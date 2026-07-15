package com.nyeras.student_dashboard.controller;

import com.nyeras.student_dashboard.dto.ProfileRequest;
import com.nyeras.student_dashboard.entity.User;
import com.nyeras.student_dashboard.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @GetMapping("/api/profile")
    public User getProfile() {
        return profileService.getProfile();
    }
@PutMapping("/api/profile")
public User updateProfile(@RequestBody ProfileRequest request) {
    return profileService.updateProfile(request);
}
}
