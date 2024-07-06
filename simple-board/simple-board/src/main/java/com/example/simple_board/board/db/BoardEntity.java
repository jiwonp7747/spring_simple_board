package com.example.simple_board.board.db;

import com.example.simple_board.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name="board") //연결할 table 이름
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String board_name;

    private String status;

    @OneToMany(
            mappedBy = "board"
    )
    //@Where(clause = "status=REGISTERED") // deprecated
    @SQLRestriction("status='REGISTERED'")
    @OrderBy("id desc")
    private List<PostEntity> postList = List.of();
}
