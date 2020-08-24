package com.example.myproject.model.entity.post;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PostPage extends PageRequest {

    public PostPage(int nowPage) {
        super(nowPage, 5, Sort.by("id").descending());
    }
}
