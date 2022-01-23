package com.example.TestLogin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private final String httpErrorCode;
    private final String message;

    public BadRequestException(String httpErrorCode, String message) {
        super(message);
        this.httpErrorCode = httpErrorCode;
        this.message = message;
    }

    public String getHttpErrorCode() {
        return httpErrorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
