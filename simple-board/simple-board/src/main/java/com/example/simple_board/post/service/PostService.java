package com.example.simple_board.post.service;

import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.common.Api;
import com.example.simple_board.common.Pagination;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.db.PostRepository;
import com.example.simple_board.post.model.PostReqeust;
import com.example.simple_board.post.model.PostVeiwRequest;
import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor //자동 생성자 주입
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
//    private final ReplyService replyService;

    public PostEntity create(
            PostReqeust postReqeust
    ){
        var boardEntity = boardRepository.findById(postReqeust.getBoardId()).get();
        var entity= PostEntity.builder() // 클라이언트로 부터 받은 데이터를 통해 PostEntity 객체를 만들고
                .board(boardEntity) // 게시판 id를 1로 설정
                .user_name(postReqeust.getUserName())
                .password(postReqeust.getPassword())
                .email(postReqeust.getEmail())
                .status("REGISTERED")
                .title(postReqeust.getTitle())
                .content(postReqeust.getContent())
                .posted_at(LocalDateTime.now())
                .build();
        return postRepository.save(entity);
    }

    /*
    * 1. 게시글이 있는가 ?
    * 2. 비밀번호가 맞는가 ?
    * */
    public PostEntity view(PostVeiwRequest postVeiwRequest) {

        return postRepository.findFirstByIdAndStatusOrderByIdDesc(postVeiwRequest.getPostId(), "REGISTERED") //옵셔널
                .map(it->{// Optional의 값이 존재할 때 람다 함수로 값을 변환하여 리턴, 존재하지 않다면 빈 optional을 리턴
                    // entity 존재
                    if(!it.getPassword().equals(postVeiwRequest.getPassword())){ //비밀번호 맞는지
                        var format="패스워드가 맞지 않습니다 %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword(), postVeiwRequest.getPassword()));
                    }

                    //답변 글도 같이 //dto를 통해 가져올 수 있기 때문에 필요 x
                    /*var replyList = replyService.findAllByPostId(it.getId());
                    it.setReplyList(replyList);*/

                    return it; //해당 엔티티 리턴
                }).orElseThrow(
                        ()->{
                            return new RuntimeException("해당 게시글이 존재하지 않습니다."+postVeiwRequest.getPostId()); //데이터(아이디에 해당하는 엔티티가 없다)
                        }
                );
    }

    public Api<List<PostEntity>> all(Pageable pageable) { // 페이지네이션 정보를 전달
        var list= postRepository.findAll(pageable); // Page<PostEntity>반환, 페이지네이션된 결과 반환

        var pagination= Pagination.builder()
                .page(list.getNumber()) //현재 몇번째 페이지
                .size(list.getSize()) // 한 번에 몇개의 글을 보여줄 것인지
                .totalElements(list.getTotalElements()) // 총 요소 수
                .totalPage(list.getTotalPages()) // 총 페이지 개수가 몇개인지
                .currentElements(list.getNumberOfElements()) // 현재 페이지의 요소 수
                .build()
                ;

        var response= Api.<List<PostEntity>>builder() // Api는 Api 응답을 나타내는 커스텀 객체
                .body(list.toList())  //페이지네이션된 결과를 리스트로 변환
                .pagination(pagination) // 페이지네이션 정보 설정
                .build();

        return response;
    }

    public void delete(PostVeiwRequest postVeiwRequest) {
         postRepository.findById(postVeiwRequest.getPostId()) //옵셔널
                .map(it->{
                    // entity 존재
                    if(!it.getPassword().equals(postVeiwRequest.getPassword())){ //비밀번호 맞는지
                        var format="패스워드가 맞지 않습니다 %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword(), postVeiwRequest.getPassword()));
                    }
                    //비밀번호가 맞는 경우
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it; //해당 엔티티 리턴 Map일 때는 리턴이 필요

                }).orElseThrow(
                        ()->{
                            return new RuntimeException("해당 게시글이 존재하지 않습니다."+postVeiwRequest.getPostId()); //데이터(아이디에 해당하는 엔티티가 없다)
                        }
                );
    }
}
