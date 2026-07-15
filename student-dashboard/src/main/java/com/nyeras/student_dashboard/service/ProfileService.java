package com.nyeras.student_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nyeras.student_dashboard.dto.ProfileRequest;
import com.nyeras.student_dashboard.entity.User;
import com.nyeras.student_dashboard.repository.UserRepository;

@Service
public class ProfileService {
    @Autowired
private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User getProfile() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email).orElse(null);
    }
public User updateProfile(ProfileRequest request) {

    String email = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

    User user = userRepository.findByEmail(email).orElse(null);

    if (user == null) {
        return null;
    }

    user.setName(request.getName());
    user.setEmail(request.getEmail());

    if (request.getPassword() != null &&
    !request.getPassword().isBlank()) {

    user.setPassword(passwordEncoder.encode(request.getPassword()));

}

    return userRepository.save(user);
}
}