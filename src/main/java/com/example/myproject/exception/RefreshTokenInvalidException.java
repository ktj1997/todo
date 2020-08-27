package com.example.myproject.exception;

public class RefreshTokenInvalidException extends RuntimeException {
    public RefreshTokenInvalidException() {
        super("RefreshToken이 유효하지 않습니다. 다시 로그인 해주십시오.");
    }
}
