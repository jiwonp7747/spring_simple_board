package com.example.simple_board.post.service;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostConverter { // 데이터 변환

    public PostDto toDto(PostEntity postEntity) {
            return PostDto.builder()
                .id(postEntity.getId())
                .user_name(postEntity.getUser_name())
                .status(postEntity.getStatus())
                .email(postEntity.getEmail())
                .password(postEntity.getPassword())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .posted_at(postEntity.getPosted_at())
                .boardId(postEntity.getBoard().getId())
                .build();
    }
}
