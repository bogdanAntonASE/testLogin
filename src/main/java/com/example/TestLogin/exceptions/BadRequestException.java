package com.example.TestLogin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException{

    public BadRequestException(String httpErrorCode, String message) {
        super(httpErrorCode, message);
    }
}

