package com.example.aggregator.api.client.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KenectLabsClientConfig {

    @Value("${feign.kenect-labs.token}")
    private String bearerToken;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate ->
                requestTemplate.header("Authorization", bearerToken);
    }
}