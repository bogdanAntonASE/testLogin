package com.example.TestLogin.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest implements Serializable {

    private final String username;
    private final String password;
}
