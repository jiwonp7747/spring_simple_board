package com.example.simple_board.crud;

import com.example.simple_board.common.Api;
import com.example.simple_board.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// dto-> entity -> dto
// 추상 클래스는 빈으로 등록 안됨
// 제네릭을 통해서 dto, Entity의 종류에 상관없이 받을 수 있다.

public abstract class CRUDAbstractService<DTO, ENTITY> implements CRUDInterface<DTO> {

    @Autowired(required = false)
    private JpaRepository<ENTITY, Long> jpaRepository; //

    @Autowired(required = false)
    private Converter<DTO, ENTITY> converter; // dto와 entity 변환 역할

    @Override
    public DTO create(DTO dto) {

        // dto -> entity
        var entity = converter.toEntity(dto);
        // entity -> save
        jpaRepository.save(entity);
        // save -> dto
        var returnDto= converter.toDTO(entity);

        return returnDto;
    }

    @Override
    public Optional<DTO> read(Long id) {

        var optionalEntity = jpaRepository.findById(id);

        var dto=optionalEntity.map(
                entity -> converter.toDTO(entity)
        ).orElseGet(()->null); // orElseGet은 value가 없는 경우

        return Optional.of(dto);
    }

    @Override
    public DTO update(DTO dto) {

        var entity = converter.toEntity(dto);
        jpaRepository.save(entity);
        var returnDto= converter.toDTO(entity);

        return returnDto;
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Api<List<DTO>> list(Pageable pageable) { // pageable이 무엇인가
        var list=jpaRepository.findAll(pageable); // Page를 리턴하는데 이 페이지가 무엇인가
        //Page<Entity> 객체에는 페이징 된 데이터 목록, 페이지 정보
        //즉 Page<Entity>는 List<Entity> + 페이지 정보
        var pagination = Pagination.builder() // 페이지 정보 추출
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        var dtoList= list.stream() // 엔티티를 추출하여 DTO로 변환
                .map(it->{
                    return converter.toDTO(it);
                }).collect(Collectors.toList());

        var response = Api.<List<DTO>>builder() // DTO와 페이지 정보를 Api 객체에 넣어서 리턴
                .body(dtoList)
                .pagination(pagination)
                .build();

        return response;
    }
}
