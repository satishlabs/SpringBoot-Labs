package com.satishlabs.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.satishlabs.dto.LoginRequest;
import com.satishlabs.dto.RegisterRequest;
import com.satishlabs.entity.User;
import com.satishlabs.repository.UserRepository;
import com.satishlabs.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;

	}

	@Override
	public String register(RegisterRequest registerRequest) {
		if (userRepository.findByUsername(registerRequest.getUsername()) != null) {
			return "Username already exists";
		}

		// Create new user and save it
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setRole("USER"); // default role
		userRepository.save(user);

		return "User registered successfully";
	}

	@Override
	public String login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		if (authentication.isAuthenticated()) {
			return "Login successful";
		} else {
			return "Invalid username or password";
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
				.password(user.getPassword()).roles(user.getRole()) // Map role directly
				.disabled(!user.isEnabled()).build();
	}

}
