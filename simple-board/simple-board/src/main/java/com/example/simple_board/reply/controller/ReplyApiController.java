package com.example.simple_board.reply.controller;

import com.example.simple_board.crud.CRUDAbstractApiController;
import com.example.simple_board.reply.db.ReplyEntity;
import com.example.simple_board.reply.model.ReplyDto;
import com.example.simple_board.reply.model.ReplyRequest;
import com.example.simple_board.reply.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyApiController extends CRUDAbstractApiController<ReplyDto, ReplyEntity> {

    /*private final ReplyService replyService;

    @PostMapping("")
    public ReplyEntity create(
            @Valid @RequestBody ReplyRequest replyRequest
            ){
        return replyService.create(replyRequest);
    }*/


}

// Controller를 만들 때 기본적인 CRUD를 만들고 싶다
// dto, entity를 만들고
// 데이터를 변환해주는 converter 만들고
// CRUDAbstractApiController 상속 받는다
// 추상 controller에 상응하는 추상 service를 상속 받는다.
