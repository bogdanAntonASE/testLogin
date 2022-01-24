package com.example.TestLogin.repository;

import com.example.TestLogin.database.RoleRepository;
import com.example.TestLogin.database.UserRepository;
import com.example.TestLogin.model.Role;
import com.example.TestLogin.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @AfterEach
    void cleanUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void findByUsernameTest() {
        String username = "user";
        Role role = new Role(null, "ADMIN");
        roleRepository.save(role);
        User user = new User(null, "user", "pass", List.of(role));
        userRepository.save(user);

        boolean isPresent = userRepository.findByUsername(username).isPresent();

        assertThat(isPresent).isTrue();
    }

    @Test
    void findByUsernameTestFailed() {
        String username = "user";
        boolean isPresent = userRepository.findByUsername(username).isPresent();

        assertThat(isPresent).isFalse();
    }
}
