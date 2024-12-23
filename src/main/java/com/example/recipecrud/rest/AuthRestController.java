package com.example.recipecrud.rest;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipecrud.JwtUtil;
import com.example.recipecrud.dao.UserDAO;
import com.example.recipecrud.entity.User;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    private final UserDAO userDAO;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthRestController(UserDAO userDAO, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.userDAO = userDAO;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        if (userDAO.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        
        user.setPassword(encoder.encode(user.getPassword()));

        userDAO.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        User existingUser = userDAO.findByEmail(user.getEmail());
        if (existingUser == null || !encoder.matches(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid email or password"));
        }
        String token = jwtUtil.generateToken(existingUser.getEmail());
        return ResponseEntity.ok(Map.of("token", token));
    }
    
}
