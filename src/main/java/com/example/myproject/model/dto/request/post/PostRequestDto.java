package com.example.myproject.model.dto.request.post;

import com.example.myproject.model.entity.post.PostType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostRequestDto {
    String title;
    String content;
    PostType type;
}
