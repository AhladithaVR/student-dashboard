package com.nyeras.student_dashboard.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtService jwtService;



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
            throws ServletException, IOException {


        String path = request.getRequestURI();


        /*
         * Skip JWT checking for:
         * - Thymeleaf pages
         * - CSS
         * - JS
         * - Images
         *
         * Only protect API requests
         */
        if (!path.startsWith("/api/")) {

            filterChain.doFilter(request, response);
            return;

        }



        String header = request.getHeader("Authorization");


        System.out.println("Authorization Header: " + header);



        if (header != null && header.startsWith("Bearer ")) {


            String token = header.substring(7);



            try {


                String email = jwtService.extractEmail(token);



                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                Collections.emptyList()
                        );



                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);



                System.out.println(
                    "Authenticated User: " + email
                );



            } catch (Exception e) {


                System.out.println("Invalid JWT Token");

                e.printStackTrace();

            }

        }



        filterChain.doFilter(request, response);

    }

}