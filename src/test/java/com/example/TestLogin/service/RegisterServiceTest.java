package com.example.TestLogin.service;

import com.example.TestLogin.database.RoleRepository;
import com.example.TestLogin.database.UserRepository;
import com.example.TestLogin.exceptions.BadRequestException;
import com.example.TestLogin.model.Role;
import com.example.TestLogin.model.User;
import com.example.TestLogin.request.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {

    private RegisterService registerService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        registerService = new RegisterService(userRepository, roleRepository, passwordEncoder);
    }

    @Test
    void registerTest() {
        RegisterRequest request = new RegisterRequest("Bogdan123", "Password123", "ADMIN");
        Role roleMock = new Role(null, request.getRole());
        User userMock = new User(null, request.getUsername(), request.getPassword(), List.of(roleMock));
        registerService.register(request);

        ArgumentCaptor<User> argumentCaptorUser = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Role> argumentCaptorRole = ArgumentCaptor.forClass(Role.class);

        verify(userRepository).save(argumentCaptorUser.capture());
        User captorValueUser = argumentCaptorUser.getValue();

        verify(roleRepository).save(argumentCaptorRole.capture());
        Role captorValueRole = argumentCaptorRole.getValue();

        assertThat(captorValueUser.getUsername()).isEqualTo(userMock.getUsername());
        assertThat(captorValueRole.getName()).isEqualTo(roleMock.getName());
    }

    @Test
    void registerTestUsernameFormatInvalid() {
        RegisterRequest request = new RegisterRequest("user", "Password123", "ADMIN");

        assertThatThrownBy(() -> registerService.register(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Username format is not correct.");

        verify(userRepository, never()).save(any());
    }

    @Test
    void registerTestPasswordFormatInvalid() {
        RegisterRequest request = new RegisterRequest("Bogdan123", "password", "ADMIN");

        assertThatThrownBy(() -> registerService.register(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Password format is not correct.");

        verify(userRepository, never()).save(any());
    }
}
