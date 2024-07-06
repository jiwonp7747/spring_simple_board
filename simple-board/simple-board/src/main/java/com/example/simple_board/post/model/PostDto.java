package com.example.simple_board.post.model;

import com.example.simple_board.board.db.BoardEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {

    private Long id;

    private Long boardId;

    private String user_name;

    private String password;

    private String email;

    private String status;

    private String title;

    private String content;

    private LocalDateTime posted_at;
}
