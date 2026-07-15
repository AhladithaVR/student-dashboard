package com.nyeras.student_dashboard.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(Authentication authentication) {

        if (authentication == null) {
            return "Authentication is NULL";
        }

        return "Logged in user: " + authentication.getName();
    }
}