package com.nyeras.student_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nyeras.student_dashboard.dto.AuthResponse;
import com.nyeras.student_dashboard.dto.LoginRequest;
import com.nyeras.student_dashboard.dto.RegisterRequest;
import com.nyeras.student_dashboard.entity.User;
import com.nyeras.student_dashboard.repository.UserRepository;
import com.nyeras.student_dashboard.security.JwtService;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
private BCryptPasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
if (userRepository.findByEmail(request.getEmail()).isPresent()) {
    throw new RuntimeException("Email already registered");
}
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
    throw new RuntimeException("Invalid Password");
}

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}