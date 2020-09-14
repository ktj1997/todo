package com.example.myproject.model.entity.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    Long id;

    String URL;

    @ManyToOne
    @JoinColumn(nullable = false)
    Post post;

    public Image(String URL, Post post) {
        this.URL = URL;
        this.post = post;
    }
}
