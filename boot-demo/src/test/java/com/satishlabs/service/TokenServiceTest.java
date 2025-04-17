package com.satishlabs.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TokenServiceTest {
	@Autowired
	private TokenService tokenService;
	
	@Test
    public void testTokenGenerationAndDecryption() {
        String email = "test@example.com";
        Integer userId = 1;
        Integer accessLevel = 3;

        String token = tokenService.generateToken(email, userId, accessLevel);
        String decryptedData = tokenService.decrypToken(token);

        Assertions.assertTrue(decryptedData.contains(email));
        Assertions.assertTrue(decryptedData.contains(userId.toString()));
        Assertions.assertTrue(decryptedData.contains(accessLevel.toString()));
    }
}
