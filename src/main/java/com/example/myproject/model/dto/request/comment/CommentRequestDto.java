package com.example.myproject.model.dto.request.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentRequestDto {
    private String content;
    private Long memoId;
}
