package com.example.myproject.model.dto.resonse.comment;

import com.example.myproject.model.dto.request.comment.CommentRequestDto;
import com.example.myproject.model.entity.comment.Comment;

public class CommentResponseDto {

    Long id;
    Long groupNum;
    String userName;
    String content;
    Boolean isDeleted;

    public CommentResponseDto(Comment comment)
    {
        this.id = comment.getId();
        this.groupNum = comment.getGroupNum();
        this.userName = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.isDeleted = comment.getDeleted();
    }

}
