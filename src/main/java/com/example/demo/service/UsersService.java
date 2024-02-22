package com.example.demo.service;

import com.example.demo.configs.JWTUtils;
import com.example.demo.models.Users;
import com.example.demo.models.enums.Role;
import com.example.demo.rep.AllRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final AllRepository rep;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    public String register(String username, String email, String password) {
        if (rep.existsByEmail(email))
            throw new RuntimeException("Email Found");
        Users user = new Users();
        user.setEmail(email);
        user.setUsername(username);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(password));
        rep.save(user);
        return "register";
    }

    public String login(String email, String password) {
        Users user = rep.findByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid Password");
        return jwtUtils.generateToken(email);
    }
}
