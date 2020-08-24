package com.example.myproject.model.dto.resonse.post;

import com.example.myproject.model.entity.post.Post;
import com.example.myproject.model.entity.post.PostType;
import com.example.myproject.model.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class PostDetailDto {
    String title;
    String content;
    Date createdAt;
    String user;
    PostType type;
    List<String> imgUrl;

    public PostDetailDto(Post post)
    {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.user = post.getUser().getUsername();
        this.type = post.getType();
        this.imgUrl = post.getImageURL().stream().map(url -> url.getURL()).collect(Collectors.toList());
    }

    @Builder
    public PostDetailDto(String title, String content, Date createdAt, User user, PostType type, List<String> imgUrl) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user.getUsername();
        this.type = type;
        this.imgUrl = imgUrl;
    }
}
