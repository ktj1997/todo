package com.example.myproject.model.dto.request.memo;

import com.example.myproject.model.entity.memo.MemoType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class MemoRequestDto {
    String title;
    String content;
    MemoType type;
}
