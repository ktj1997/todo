package com.example.myproject.exception;

public class AlreadyAuthenticatedException extends RuntimeException {
    public AlreadyAuthenticatedException() {
        super("이미 인증이 완료되었습니다.");
    }
}
