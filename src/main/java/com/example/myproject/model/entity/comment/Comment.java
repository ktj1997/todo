package com.example.myproject.model.entity.comment;

import com.example.myproject.model.dto.request.comment.CommentRequestDto;
import com.example.myproject.model.entity.post.Post;
import com.example.myproject.model.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String content;

    @Setter
    int reCommentNum = 0;

    @Setter
    Long groupNum;

    Boolean deleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @ManyToOne
    Comment parentComment;

    @ManyToOne
    Post post;

    @ManyToOne
    User user;

    @Builder
    public Comment(String content, Comment parentComment, Long groupNum, Post post, User user) {
        this.content = content;
        this.parentComment = parentComment;
        this.groupNum = groupNum;
        this.post = post;
        this.user = user;
    }

    public void updateComment(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }

    public void deleteComment() {
        this.deleted = true;
    }
}
