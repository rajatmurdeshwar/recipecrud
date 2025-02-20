package com.example.recipecrud.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.recipecrud.JwtUtil;
import com.example.recipecrud.dao.UserRepository;
import com.example.recipecrud.entity.LoginRequest;
import com.example.recipecrud.entity.User;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String signUp(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String login(LoginRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isEmpty() || !passwordEncoder.matches(request.getPassword(), existingUser.get().getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return jwtUtil.generateToken(existingUser.get().getEmail());
    }

    public Map<String, Object> getUserDetails(String token) {
        String email = jwtUtil.extractUsername(token);
        User existingUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        return Map.of(
            "name", existingUser.getName(),
            "email", existingUser.getEmail(),
            "city", existingUser.getCity(),
            "phone", existingUser.getPhonenumber()
        );
    }
}

