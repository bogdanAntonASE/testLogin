package com.example.TestLogin.controller;

import com.example.TestLogin.request.LoginRequest;
import com.example.TestLogin.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void login(@RequestBody LoginRequest loginRequest) {
        loginService.login(loginRequest);
    }
}
