package com.satishlabs.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satishlabs.dto.UserDetailsResponse;
import com.satishlabs.dto.UserUpdateRequest;
import com.satishlabs.dto.UserUpdateResponse;
import com.satishlabs.model.User;
import com.satishlabs.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	
	@GetMapping("/get")
	public UserDetailsResponse getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

		return new UserDetailsResponse(user.getUsername(), user.getRoles());
	}


	@PutMapping("/update")
	public UserUpdateResponse updateUser(@RequestBody UserUpdateRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

		if (request.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(request.getPassword()));
		}

		user.setRoles(request.getRoles());
		userRepository.save(user);

		return new UserUpdateResponse("User updated successfully");
	}
}
