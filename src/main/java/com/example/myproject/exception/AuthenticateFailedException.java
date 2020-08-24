package com.example.myproject.exception;

public class AuthenticateFailedException extends RuntimeException {
    public AuthenticateFailedException() {
        super("인증에 실패하였습니다.");
    }
}
