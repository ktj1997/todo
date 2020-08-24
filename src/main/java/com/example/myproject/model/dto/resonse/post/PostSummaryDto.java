package com.example.myproject.model.dto.resonse.post;

import com.example.myproject.model.entity.post.Post;
import com.example.myproject.model.entity.post.PostType;
import com.example.myproject.model.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class PostSummaryDto {

    String title;
    String content;
    Date createdAt;
    String user;
    PostType type;


    public PostSummaryDto(Post post)
    {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.user = post.getUser().getUsername();
        this.type = post.getType();
    }


    @Builder
    public PostSummaryDto(String title, String content, Date createdAt, User user, PostType type) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user.getUsername();
        this.type = type;
    }
}
