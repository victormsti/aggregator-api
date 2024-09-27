package com.example.aggregator.api.builder;

import com.example.aggregator.api.controller.request.AuthRequest;

public class AuthRequestBuilder {

    public static AuthRequest buildValidAuthRequest(){
        return AuthRequest.builder()
                .username("bestuser")
                .password("bestpassword")
                .build();
    }
}
