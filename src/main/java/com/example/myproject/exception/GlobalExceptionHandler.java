package com.example.myproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse userNotExistException(UserNotExistException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse userAlreadyExistException(UserAlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(value = MemoNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse memoNotExistException(MemoNotExistException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(value = CommentNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse commentNotExistException(UserAlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse ArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(value = InvalidFileTypeException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ExceptionResponse InvalidFileTypeException(InvalidFileTypeException e) {
        return new ExceptionResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, e);
    }

    @ExceptionHandler(value = InvalidUserInfoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse InvalidUserInfoException(InvalidUserInfoException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse AccessDeniedException(AccessDeniedException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(value = AlreadyAuthenticatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse AlreadyAuthenticatedException(AlreadyAuthenticatedException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(value = AuthenticateFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse AuthenticateFailedException(AuthenticateFailedException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, e);
    }
}
