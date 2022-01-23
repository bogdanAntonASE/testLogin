package com.example.TestLogin.controller;

import com.example.TestLogin.request.RegisterRequest;
import com.example.TestLogin.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/register")
public class RegisterController {

    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(RegisterRequest request) {
        registerService.register(request);
    }
}
