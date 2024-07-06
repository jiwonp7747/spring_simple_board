package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 스프링의 service 빈으로 등록
@RequiredArgsConstructor //자동으로 생성자 주입
public class BoardService {

    private final BoardRepository boardRepository; //final을 붙여야 기본 생성자에 해당 값이 들어가서 생성 // fianl은 생성자에서 초기화 돼야함
    private final BoardConverter boardConverter;

    public BoardDto create( // 클라이언트로 전달받은 객체를 새로운 객체로 생성하여 레포에 저장
            BoardRequest boardRequest
    ){
        var entity=BoardEntity.builder() // 빌더를 통해서 BoardEntity 객체를 생성
                .board_name(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        var saveEntity = boardRepository.save(entity);
        return boardConverter.toDto(saveEntity);
    }

    public BoardDto view(Long id) {
        var entity=boardRepository.findById(id).get();
        return boardConverter.toDto(entity);
    }
}
