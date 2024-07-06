package com.example.simple_board.reply.db;

import com.example.simple_board.post.db.PostEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name="reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private PostEntity post; //post + id => post_id // 외래 키 컬럼이 자동으로 추가되는 개념

    private String userName;

    private String password;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT") //속성 맞추기
    private String content;

    private LocalDateTime repliedAt;
}
