package com.example.TestLogin.service;

import com.example.TestLogin.database.RoleRepository;
import com.example.TestLogin.database.UserRepository;
import com.example.TestLogin.exceptions.BadRequestException;
import com.example.TestLogin.model.Role;
import com.example.TestLogin.model.User;
import com.example.TestLogin.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Logger logger = LogManager.getLogger(RegisterService.class);


    public void register(RegisterRequest request) {
        logger.info("Registration process starting...");
        boolean isValidUsername = request.getUsername().matches("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
        boolean isValidPassword = request.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        if (!isValidUsername) {
            logger.error("Username format is not correct.");
            throw new BadRequestException("400", "Username format is not correct.");
        }
        if (!isValidPassword) {
            logger.error("Password format is not correct.");
            throw new BadRequestException("400", "Password format is not correct.");
        }

        boolean present = userRepository.findByUsername(request.getUsername()).isPresent();
        if (present) {
            logger.error("Username is already present in database.");
            throw new BadRequestException("400", "Username is already present in database.");
        }
        String password = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(password);
        Role role = new Role(null, request.getRole());
        roleRepository.save(role);
        logger.info(String.format("Role with name %s was saved in database...", role.getName()));

        user.getRoles().add(role);
        userRepository.save(user);
        logger.info(String.format("User with username %s was saved in database...", user.getUsername()));
    }
}
