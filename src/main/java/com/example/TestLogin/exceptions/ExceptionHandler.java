package com.example.TestLogin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleRequestException(BadRequestException ex) {
        ExceptionPayload exceptionPayload = new ExceptionPayload(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionPayload, HttpStatus.BAD_REQUEST);
    }
}
