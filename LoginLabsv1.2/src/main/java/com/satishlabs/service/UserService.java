package com.satishlabs.service;

import com.satishlabs.dto.LoginRequest;
import com.satishlabs.dto.RegisterRequest;


public interface UserService {
    String register(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);
}
