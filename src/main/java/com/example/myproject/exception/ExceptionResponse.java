package com.example.myproject.exception;


import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import java.util.Date;

@Getter
@ToString
public class ExceptionResponse
{
    String message;
    int code;
    Date timestamp = new Date();

    public ExceptionResponse(HttpStatus status,Exception e)
    {
        message = e.getMessage();
        code = status.value();
    }
}
