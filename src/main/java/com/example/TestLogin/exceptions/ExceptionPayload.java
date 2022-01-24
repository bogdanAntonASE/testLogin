package com.example.TestLogin.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionPayload {
    private final String message;
    private final HttpStatus httpStatus;

    private final LocalDateTime timestamp;

    public ExceptionPayload(String message,
                            HttpStatus httpStatus,
                            LocalDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
