package com.example.simple_board.post.db;


import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name ="post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore // Json 생성으로 인한 무한 루프 방지
    @ToString.Exclude // ToString 사용시 무한 루프 방지
    //@JoinColumn(name = "board_id")
    private BoardEntity board; // 시스템상으로 뒤에 +_id

    private String user_name;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT") //속성 맞추기
    private String content;

    private LocalDateTime posted_at;

    /*@OneToMany(
            mappedBy = "post"
    )*/
    //@Transient // 데이터베이스의 컬럼으로 사용하지 않겠다.
    @OneToMany(
            mappedBy = "post"
    )
    private List<ReplyEntity> replyList= List.of();

    // Entity에 들어가 있는 변수들은 모두 테이블의 column으로 인식
    // 따라서 replyList를 인식하지 못하게 만들어야 함.
}
