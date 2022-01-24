package com.example.TestLogin.service;

import com.example.TestLogin.database.RoleRepository;
import com.example.TestLogin.database.UserRepository;
import com.example.TestLogin.exceptions.BadRequestException;
import com.example.TestLogin.model.Role;
import com.example.TestLogin.model.User;
import com.example.TestLogin.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
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

    public String register(RegisterRequest request) {
        boolean isValid = request.getUsername().matches("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
        if (!isValid) {
            throw new BadRequestException("400", "Username format is not correct.");
        }

        boolean present = userRepository.findByUsername(request.getUsername()).isPresent();
        if (present) {
            throw new BadRequestException("400", "Username is already present in database.");
        }
        String password = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(password);
        Role role = new Role(null, request.getRole());
        roleRepository.save(role);

        user.getRoles().add(role);
        userRepository.save(user);
        return "";
    }
}
