package com.example.myproject.exception;

public class CommentNotExistException extends RuntimeException {
    public CommentNotExistException() {
        super("댓글이 존재하지 않습니다.");
    }
}
