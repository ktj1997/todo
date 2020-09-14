package com.example.myproject.model.entity.post;

import com.example.myproject.model.dto.request.post.PostRequestDto;
import com.example.myproject.model.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private int commentNum = 0;

    @Enumerated(EnumType.STRING)
    private PostType type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @Setter
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Image> imageURL;

    @Builder
    public Post(String title, String content, List<Image> imageUrl, PostType type, User user) {
        this.title = title;
        this.content = content;
        this.imageURL = imageUrl;
        this.type = type;
        this.user = user;
    }

    public void updateMemo(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.type = postRequestDto.getType();
        this.content = postRequestDto.getContent();
    }
}
