package com.example.myproject.exception;

public class MemoNotExistException extends RuntimeException {
    public MemoNotExistException() {
        super("존재하지 않는 메모입니다.");
    }
}
