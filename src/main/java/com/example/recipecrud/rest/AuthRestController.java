package com.example.recipecrud.rest;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @PostMapping("/userDetails")
    public ResponseEntity<?> getUserDetails(@RequestHeader("Authorization") String authHeader) {
        try {
            // Extract the token from the Authorization header
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                     .body(Map.of("error", "Missing or invalid Authorization header"));
            }
            String token = authHeader.substring(7); // Remove "Bearer " prefix
    
            // Validate and parse the JWT
            String email = jwtUtil.extractUsername(token); // Assuming your JWT utility has this method
    
            // Fetch the user by email
            User existingUser = userDAO.findByEmail(email);
            if (existingUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(Map.of("error", "User not found"));
            }
    
            // Return user details (avoid sensitive fields like password)
            Map<String, Object> userDetails = Map.of(
                "name", existingUser.getName(),
                "email", existingUser.getEmail(),
                "city", existingUser.getCity(),
                "phone", existingUser.getPhonenumber()
            );
    
            return ResponseEntity.ok(userDetails);
    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(Map.of("error", "Invalid token"));
        }
    }
    
    
}
