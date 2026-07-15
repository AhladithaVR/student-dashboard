package com.nyeras.student_dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyeras.student_dashboard.dto.AuthResponse;
import com.nyeras.student_dashboard.dto.LoginRequest;
import com.nyeras.student_dashboard.dto.RegisterRequest;
import com.nyeras.student_dashboard.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

@PostMapping("/register")
public AuthResponse register(@Valid@RequestBody RegisterRequest request) {

    System.out.println("REGISTER API HIT");

    return authService.register(request);
}

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}