package com.example.myproject.model.entity.memo;

import com.example.myproject.model.dto.request.memo.MemoRequestDto;
import com.example.myproject.model.entity.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private int commentNum = 0;

    @Enumerated(EnumType.STRING)
    private MemoType type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @ManyToOne
    User user;

    @OneToMany(mappedBy = "memo",cascade = CascadeType.REMOVE)
    List<Image> imageURL;

    @Builder
    public Memo(String title, String content, List<Image> imageUrl, MemoType type, User user) {
        this.title = title;
        this.content = content;
        this.imageURL = imageUrl;
        this.type = type;
        this.user = user;
    }

    public void updateMemo(MemoRequestDto memoRequestDto)
    {
        this.title = memoRequestDto.getTitle();
        this.type = memoRequestDto.getType();
        this.content = memoRequestDto.getContent();
    }
}
