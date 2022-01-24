package com.example.TestLogin.service;

import com.example.TestLogin.database.RoleRepository;
import com.example.TestLogin.database.UserRepository;
import com.example.TestLogin.exceptions.BadRequestException;
import com.example.TestLogin.model.Role;
import com.example.TestLogin.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Logger logger = LogManager.getLogger(UserService.class);

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user by username from database...");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found in database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        User user = optionalUser.get();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public void addRoleToUser(String username, String roleName) {
        logger.info(String.format("Adding role to user %s", username));
        boolean isUserPresent = userRepository.findByUsername(username).isPresent();
        if (!isUserPresent) {
            logger.info(String.format("User %s not found in database...", username));
            throw new BadRequestException("400", "User not found in database");
        }
        User user = userRepository.findByUsername(username).get();
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
