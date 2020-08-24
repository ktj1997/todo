package com.example.myproject.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("이미 존재하는 사용자 입니다.");
    }
}
