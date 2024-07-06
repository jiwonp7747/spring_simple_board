package com.example.simple_board.common;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Api<T> { // 제네릭 클래스 //API 응답을 구조화하기 위해서 필요
    //여기서는 페이지네이션 정보와, Entity 리스트 정보를 담기 위해서 사용한다.

    private T body; // 다양한 타입 저장 가능

    private Pagination pagination; // 페이지네이션 정보를 담는다.

}
