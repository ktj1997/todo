package com.example.myproject.model.entity.comment;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class CommentPage extends PageRequest {


    public CommentPage(int nowPage)
    {
        super(nowPage,5, Sort.by("groupNum").descending().and(Sort.by("id").descending()));
    }
}
