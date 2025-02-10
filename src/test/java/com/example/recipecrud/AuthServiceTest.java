package com.example.recipecrud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.recipecrud.dao.UserRepository;
import com.example.recipecrud.entity.User;
import com.example.recipecrud.service.AuthService;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    
    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private JwtUtil jwtUtil;

    @InjectMocks private AuthService authService;
    


    @Test
    void signUp_shouldRegisterUserSuccessfully() {
        User user = new User(1L, "John Doe", "1234567890", "New York", "test@example.com", "password123");
        
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        String result = authService.signUp(user);

        verify(userRepository).save(any(User.class));
        assertEquals("User registered successfully!", result);
    }

    @Test
    void signUp_shouldFailIfEmailExists() {
        User user = new User(1L, "John Doe", "1234567890", "New York", "test@example.com", "password123");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> authService.signUp(user));
        assertEquals("Email already exists", exception.getMessage());
    }

    // @Test
    // void login_shouldReturnTokenWhenCredentialsAreCorrect() {
    //     User user = new User(1L, "John Doe", "1234567890", "New York", "test@example.com", "password123");
    //     user.setPassword("encodedPassword");
        
    //     when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
    //     when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);
    //     when(jwtUtil.generateToken(user.getEmail())).thenReturn("mocked-jwt-token");

    //     String token = authService.login(user);

    //     assertEquals("mocked-jwt-token", token);
    // }

    @Test
    void login_shouldFailIfUserNotFound() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> authService.login(new User(1L, "John Doe", "1234567890", "New York", "test@example.com", "password123")
            ));

        assertEquals("Invalid email or password", exception.getMessage());
    }

    // @Test
    // void login_shouldFailIfPasswordIsIncorrect() {
    //     User user = new User("test@example.com", "password123");
    //     user.setPassword("encodedPassword");

    //     when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
    //     when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

    //     Exception exception = assertThrows(IllegalArgumentException.class, 
    //         () -> authService.login(new User("test@example.com", "wrongPassword")));

    //     assertEquals("Invalid email or password", exception.getMessage());
    // }

    @Test
    void getUserDetails_shouldReturnUserDetails() {
        User user = new User(1L, "John Doe", "1234567890", "New York", "test@example.com", "password123");
        when(jwtUtil.extractUsername("valid-token")).thenReturn(user.getEmail());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        Map<String, Object> userDetails = authService.getUserDetails("valid-token");

        assertEquals(user.getEmail(), userDetails.get("email"));
    }

    @Test
    void getUserDetails_shouldFailIfTokenIsInvalid() {
        when(jwtUtil.extractUsername("invalid-token")).thenThrow(new RuntimeException("Invalid token"));

        Exception exception = assertThrows(RuntimeException.class, () -> authService.getUserDetails("invalid-token"));

        assertEquals("Invalid token", exception.getMessage());
    }

}
