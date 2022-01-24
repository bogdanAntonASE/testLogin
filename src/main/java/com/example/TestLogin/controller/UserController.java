package com.example.TestLogin.controller;

import com.example.TestLogin.model.Role;
import com.example.TestLogin.model.User;
import com.example.TestLogin.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/role")
    public void saveRole(String roleName) {
        userService.saveRole(new Role(null, roleName));
    }

    @PostMapping("/role/user")
    public void saveRoleForUser(String roleName, String username) {
        userService.addRoleToUser(username, roleName);
    }
}
