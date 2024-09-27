package com.example.aggregator.api.service.contract;

import com.example.aggregator.api.controller.response.token.TokenResponse;

public interface AuthService {

    TokenResponse login(String username, String password);
}
