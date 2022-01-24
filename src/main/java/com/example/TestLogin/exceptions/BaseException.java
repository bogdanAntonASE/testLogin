package com.example.TestLogin.exceptions;

public abstract class BaseException extends RuntimeException{

    private final String httpErrorCode;
    private final String message;

    public BaseException(String httpErrorCode, String message) {
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
