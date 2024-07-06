package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardConverter { // 데이터 변환의 역할

    private final PostConverter postConverter;

    public BoardDto toDto(BoardEntity boardEntity){

        var postList=boardEntity.getPostList().stream() // 스트림으로 변환
                .map(postEntity -> {
                    return postConverter.toDto(postEntity);
                }).collect(Collectors.toList()); // 변환된 PostDto 객체 리스트로 수집

            return BoardDto.builder()
                .id(boardEntity.getId())
                .board_name(boardEntity.getBoard_name())
                .status(boardEntity.getStatus())
                .postList(postList) //boardEntity.getPostList()
                .build()
                ;
    }
}
