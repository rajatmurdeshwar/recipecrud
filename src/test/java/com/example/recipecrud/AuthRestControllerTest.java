package com.example.recipecrud;

import com.example.recipecrud.dao.UserRepository;
import com.example.recipecrud.entity.User;
import com.example.recipecrud.rest.AuthRestController;
import com.example.recipecrud.service.AuthService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthRestControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock private AuthService authService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthRestController authRestController;

    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = new User(5L, "John po", "1234567890", "New York", "john.pp@example.com", "password123");
    }
/** 
    @Test
    void testSignUp_Success() {
        when(userRepository.findByEmail(mockUser.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(mockUser.getPassword())).thenReturn("hashed_password");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        ResponseEntity<String> response = authRestController.signUp(mockUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully!", response.getBody());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testSignUp_EmailAlreadyExists() {
        when(userRepository.findByEmail(mockUser.getEmail())).thenReturn(Optional.of(mockUser));

        ResponseEntity<String> response = authRestController.signUp(mockUser);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email already exists", response.getBody());

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testLogin_Success() {
        when(userRepository.findByEmail(mockUser.getEmail())).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches(mockUser.getPassword(), mockUser.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(mockUser.getEmail())).thenReturn("mock_token");

        ResponseEntity<Map<String, String>> response = authRestController.login(mockUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("mock_token", response.getBody().get("token"));
    }

    @Test
    void testLogin_InvalidCredentials() {
        when(userRepository.findByEmail(mockUser.getEmail())).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches(mockUser.getPassword(), mockUser.getPassword())).thenReturn(false);

        ResponseEntity<Map<String, String>> response = authRestController.login(mockUser);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid email or password", response.getBody().get("error"));
    }

    @Test
    void testLogin_UserNotFound() {
        when(userRepository.findByEmail(mockUser.getEmail())).thenReturn(Optional.empty());

        ResponseEntity<Map<String, String>> response = authRestController.login(mockUser);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid email or password", response.getBody().get("error"));
    }

    @Test
    void testGetUserDetails_Success() {
        String token = "mock_token";
        when(jwtUtil.extractUsername(token)).thenReturn(mockUser.getEmail());
        when(userRepository.findByEmail(mockUser.getEmail())).thenReturn(Optional.of(mockUser));

        ResponseEntity<?> response = authRestController.getUserDetails("Bearer " + token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
        assertEquals(mockUser.getEmail(), responseBody.get("email"));
    }

    @Test
    void testGetUserDetails_InvalidToken() {
        ResponseEntity<?> response = authRestController.getUserDetails("Bearer invalid_token");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid token", ((Map<?, ?>) response.getBody()).get("error"));
    }

    @Test
    void testGetUserDetails_UserNotFound() {
        String token = "mock_token";
        when(jwtUtil.extractUsername(token)).thenReturn(mockUser.getEmail());
        when(userRepository.findByEmail(mockUser.getEmail())).thenReturn(Optional.empty());

        ResponseEntity<?> response = authRestController.getUserDetails("Bearer " + token);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", ((Map<?, ?>) response.getBody()).get("error"));
    } */
}


