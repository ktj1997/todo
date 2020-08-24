package com.example.myproject.model.entity.memo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class MemoPage extends PageRequest {

    public MemoPage(int nowPage) {
        super(nowPage, 5, Sort.by("id").descending());
    }
}
