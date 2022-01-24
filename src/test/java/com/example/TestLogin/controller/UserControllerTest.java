package com.example.TestLogin.controller;

import com.example.TestLogin.model.User;
import com.example.TestLogin.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @Test
    void getAllTest() {

        UserController userController = new UserController(userService);
        ResponseEntity<List<User>> userControllerAll = userController.getAll();

        assertEquals(Collections.emptyList(), userControllerAll.getBody());
    }
}
