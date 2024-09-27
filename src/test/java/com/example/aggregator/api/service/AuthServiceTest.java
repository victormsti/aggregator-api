package com.example.aggregator.api.service;

import com.example.aggregator.api.base.AbstractTest;
import com.example.aggregator.api.configuration.security.JwtUtil;
import com.example.aggregator.api.controller.response.token.TokenResponse;
import com.example.aggregator.api.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthServiceTest extends AbstractTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    public void whenCallMethodLoginThenShouldReturnValidToken(){
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(expectedUserDetails, null, expectedUserDetails.getAuthorities()));
        when(userDetailsService.loadUserByUsername(expectedUsername)).thenReturn(expectedUserDetails);
        when(jwtUtil.generateToken(expectedUsername)).thenReturn(expectedValidToken);

        TokenResponse result = authService.login(expectedUsername, expectedPassword);

        Assertions.assertEquals(expectedValidToken, result.getToken());
    }
}
