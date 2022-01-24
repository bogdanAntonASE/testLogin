package com.example.TestLogin.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Configuration
public class PasswordEncoder extends BCryptPasswordEncoder {

    @Value("${seed}")
    private String secretSeed;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(BCryptVersion.$2Y, 10, new SecureRandom(secretSeed.getBytes(StandardCharsets.UTF_8)));
    }
}
