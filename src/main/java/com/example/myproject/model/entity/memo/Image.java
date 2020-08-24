package com.example.myproject.model.entity.memo;

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
    Memo memo;

    public Image(String URL, Memo memo) {
        this.URL = URL;
        this.memo = memo;
    }
}
