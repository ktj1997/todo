package com.example.myproject.exception;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException()
    {
        super("존재하지 않는 사용자 입니다.");
    }
}
