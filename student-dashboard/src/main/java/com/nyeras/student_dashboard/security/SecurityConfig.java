package com.nyeras.student_dashboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
            .csrf(csrf -> csrf.disable())


            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )


            .authorizeHttpRequests(auth -> auth


                // Thymeleaf pages
                .requestMatchers(
                    "/",
                    "/dashboard",
                    "/students",
                    "/tasks",
                    "/attendance",
                    "/reports",
                    "/profile",
                    "/settings"
                ).permitAll()


                // Static files
                .requestMatchers(
                    "/css/**",
                    "/js/**",
                    "/images/**"
                ).permitAll()


                // Login/Register API
                .requestMatchers(
                    "/api/auth/**"
                ).permitAll()


                // Secure APIs
                .requestMatchers(
                    "/api/**"
                ).authenticated()


                .anyRequest().permitAll()

            )


            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );


        return http.build();
    }
}