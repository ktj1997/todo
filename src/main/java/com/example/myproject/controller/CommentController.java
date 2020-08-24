package com.example.myproject.controller;

import com.example.myproject.model.dto.request.comment.CommentRequestDto;
import com.example.myproject.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memo/{memoId}")
public class CommentController {

    private final CommentService commentService;

    /*
        댓글 작성
     */
    @PostMapping
    public void createComment(@PathVariable Long memoId, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.createComment(commentRequestDto);
    }

    /*
        대댓글 작성
     */
    @PostMapping("/comment/{commentId}")
    public void createReComment(@PathVariable Long memoId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.createReComment(memoId, commentId, commentRequestDto);
    }

    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable Long memoId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {

    }
}
