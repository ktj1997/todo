package com.example.myproject.exception;

public class InvalidUserInfoException extends RuntimeException{

    public InvalidUserInfoException()
    {
        super("가입하지 않은 아이디이거나, 잘못된 비밀번호 입니다.");
    }
}
