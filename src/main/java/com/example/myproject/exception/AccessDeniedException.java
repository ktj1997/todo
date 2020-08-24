package com.example.myproject.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException()
    {
        super("수정/삭제 권한이 없습니다.");
    }
}
