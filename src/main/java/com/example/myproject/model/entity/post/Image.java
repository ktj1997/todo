package com.example.myproject.model.entity.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    Post post;

    public Image(String URL, Post post) {
        this.URL = URL;
        this.post = post;
    }
}
