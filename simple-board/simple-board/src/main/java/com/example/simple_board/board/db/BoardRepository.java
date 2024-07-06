package com.example.simple_board.board.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // JpaRepository를 확장하고 BoardEntity클래스를 다룬다, Long타입의 id 가진다.
    // JpaRepositoyr는 기본적으로 CRUD 제공(save, findById, findAll, deleteById)
}
