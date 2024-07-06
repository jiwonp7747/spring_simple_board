package com.example.simple_board.reply.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

    // select * from reply where post_id=? and status=? order by id desc  // 최신 순으로 가져오기
    // 엔티티의 이름에 따라서 가져온다. 엔티티가 카멜 케이스이면 카멜 케이스로 가져와야함
    List<ReplyEntity> findAllByPostIdAndStatusOrderByIdDesc(Long postId, String status);
}
