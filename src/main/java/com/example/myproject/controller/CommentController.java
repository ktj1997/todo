package com.example.myproject.controller;

import com.example.myproject.model.dto.request.comment.CommentRequestDto;
import com.example.myproject.service.comment.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/{postId}")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation("댓글작성")
    @PostMapping
    public void createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.createComment(postId, commentRequestDto);
    }

    @ApiOperation("대 댓글 작성")
    @PostMapping("/comment/{commentId}")
    public void createReComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.createReComment(postId, commentId, commentRequestDto);
    }

    @ApiOperation("댓글 수정")
    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.updateComment(commentId, commentRequestDto);
    }

    @ApiOperation("댓글 삭제")
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);
    }
}
