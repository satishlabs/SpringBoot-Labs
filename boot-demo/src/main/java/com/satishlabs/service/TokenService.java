package com.satishlabs.service;

import org.springframework.stereotype.Service;

import com.satishlabs.util.EncryptionUtil;

@Service
public class TokenService {
	private static final String SECRET_KEY = "mySecretKey123456";
	
	public String generateToken(String email,Integer userId, Integer accessLevel) {
		String data = email + "|" + userId + "|" + accessLevel + "|" + System.currentTimeMillis();
        return EncryptionUtil.encrypt(data, SECRET_KEY);
	}
	
	public String decrypToken(String token) {
		return EncryptionUtil.decrypt(token,SECRET_KEY);
	}
}
