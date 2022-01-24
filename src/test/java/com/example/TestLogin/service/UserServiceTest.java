package com.example.TestLogin.service;

import com.example.TestLogin.database.RoleRepository;
import com.example.TestLogin.database.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, roleRepository);
    }

    @Test
    public void loadByUsernameTest() {

    }

    @Test
    public void getAllUsers() {
        userService.getUsers();
        verify(userRepository).findAll();
    }
}
