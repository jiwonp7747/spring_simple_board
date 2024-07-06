package com.example.simple_board.post.controller;

import com.example.simple_board.common.Api;
import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostReqeust;
import com.example.simple_board.post.model.PostVeiwRequest;
import com.example.simple_board.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController // 컨트롤러인데 데이터를 반환하는 컨트롤러
@RequestMapping("/api/post") // 컨트롤러의 모든 http 메서드가 기본 경로로 가진다
@RequiredArgsConstructor //final @NotNull 필드 포함된 생성자를 생성, 주입
public class PostApiController {

    private final PostService postService;

    @PostMapping("")
    public PostEntity create(
            @Valid // PostRequest 객체 옳바른지 검증
            @RequestBody //요정 데이터를 PostRequest 객체 매핑
            PostReqeust postReqeust
    ){
        return postService.create(postReqeust); //서비스를 통해서 비즈니스 로직 수행
    }

    //@GetMapping("/id/{id}") // 비공개 게시글인 경우 요청할 때 비밀번호를 보내야 하는데 이러면 get 사용 x
    @PostMapping("/view")
    public PostEntity view(
            @Valid
            @RequestBody PostVeiwRequest postVeiwRequest //요청 데이터를 PostViewRequest로 받는다
        ){

        return postService.view(postVeiwRequest);
    }

    @GetMapping("/all")
    public Api<List<PostEntity>> list(
        @PageableDefault(page =0, size = 10, sort = "id", direction = Sort.Direction.DESC) // 페이지처리 위해서
        Pageable pageable
    ){
        return postService.all(pageable);
    }

    // 삭제도 비밀번호가 필요하기 때문에 get x
    @PostMapping("/delete")
    public void delete(
            @Valid
            @RequestBody PostVeiwRequest postVeiwRequest
    ){
        postService.delete(postVeiwRequest);
    }
}
