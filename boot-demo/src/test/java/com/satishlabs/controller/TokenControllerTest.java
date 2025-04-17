package com.satishlabs.controller;

import com.satishlabs.entity.UserEntity;
import com.satishlabs.repository.UserRepository;
import com.satishlabs.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class TokenControllerTest {

    @InjectMocks
    private TokenController tokenController;

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRequestToken_Success(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setEmail("satish@gmail");
        userEntity.setAccessLevel(3);
        userEntity.setStatus(UserEntity.Status.ACTIVE);

        when(userRepository.findByIdAndStatus(eq(1),eq(UserEntity.Status.ACTIVE))).thenReturn(Optional.of(userEntity));
        when(tokenService.generateToken(eq("satish@gmail"),eq(1),eq(3))).thenReturn("mockedToken");

        ResponseEntity<String> response = tokenController.requestToken(1);

        verify(userRepository,times(1)).findByIdAndStatus(1, UserEntity.Status.ACTIVE);
        verify(tokenService, times(1)).generateToken("satish@gmail",1,3);

        //assertEquals(200, response.getStatusCodeValue());
        assertEquals("mockedToken", response.getBody());

    }

    @Test
    public void testRequestToken_UserNotFound(){
        when(userRepository.findByIdAndStatus(eq(1), eq(UserEntity.Status.ACTIVE))).thenReturn(Optional.empty());

        ResponseEntity<String> response = tokenController.requestToken(1);

        verify(userRepository, times(1)).findByIdAndStatus(1, UserEntity.Status.ACTIVE);
        verify(tokenService, never()).generateToken(anyString(), anyInt(), anyInt());

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid or InActive User",response.getBody());
    }

    @Test
    public void testExchangeToken_Success() {
        // Mock service behavior
        when(tokenService.decrypToken(eq("validToken")))
                .thenReturn("decryptedData");

        // Call the API
        ResponseEntity<String> response = tokenController.exchangeToken("validToken");

        // Verify and assert
        verify(tokenService, times(1)).decrypToken("validToken");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("decryptedData", response.getBody());
    }

    @Test
    public void testExchangeToken_InvalidToken(){
        when(tokenService.decrypToken(eq("invalidToken"))).thenThrow(new RuntimeException("Invalid token"));

        ResponseEntity<String> response = tokenController.exchangeToken("invalidToken");

        verify(tokenService, times(1)).decrypToken("invalidToken");

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid token", response.getBody());
    }
}
