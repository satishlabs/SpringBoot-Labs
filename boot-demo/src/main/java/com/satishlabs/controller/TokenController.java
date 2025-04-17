package com.satishlabs.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satishlabs.entity.UserEntity;
import com.satishlabs.repository.UserRepository;
import com.satishlabs.service.TokenService;

@RestController
@RequestMapping("/tokenService")
public class TokenController {
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	//http://localhost:8080/tokenService/requestToken/1
	@GetMapping("/requestToken/{userId}")
	public ResponseEntity<String> requestToken(@PathVariable Integer userId){
		Optional<UserEntity> userOptional = userRepository.findByIdAndStatus(userId, UserEntity.Status.ACTIVE);
		if(userOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or InActive User");
		}
		
		UserEntity user = userOptional.get();
		String token = tokenService.generateToken(user.getEmail(), user.getId(), user.getAccessLevel());
		return ResponseEntity.ok(token);
	}
	
	@GetMapping("/exchangeToken/{token}")
	public ResponseEntity<String> exchangeToken(@PathVariable String token){
		try {
			String decryptedData = tokenService.decrypToken(token);
			return ResponseEntity.ok(decryptedData);
		}catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
		}
	}
}
