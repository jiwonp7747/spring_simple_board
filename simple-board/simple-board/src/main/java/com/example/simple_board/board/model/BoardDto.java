package com.example.simple_board.board.model;

import com.example.simple_board.post.db.PostEntity;
import com.example.simple_board.post.model.PostDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardDto {
    private long id;

    private String board_name;

    private String status;

    private List<PostDto> postList = List.of();
}
