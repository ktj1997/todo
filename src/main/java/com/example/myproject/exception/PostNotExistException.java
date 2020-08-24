package com.example.myproject.exception;

public class PostNotExistException extends RuntimeException {
    public PostNotExistException() {
        super("존재하지 않는 메모입니다.");
    }
}
