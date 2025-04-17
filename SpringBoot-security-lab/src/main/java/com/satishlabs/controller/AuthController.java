package com.satishlabs.controller;

import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.satishlabs.dto.LoginRequest;
import com.satishlabs.dto.LoginResponse;
import com.satishlabs.dto.RegisterRequest;
import com.satishlabs.dto.RegisterResponse;
import com.satishlabs.jwt.JwtUtil;
import com.satishlabs.model.User;
import com.satishlabs.service.CustomUserDetailsService;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return new LoginResponse(token, "Login successful");
    }
    
    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        String result = customUserDetailsService.registerUser(request.getUsername(), request.getPassword(), request.getRoles());
        return new RegisterResponse(result);
    }
}
