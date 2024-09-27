package com.example.aggregator.api.builder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class UserDetailsBuilder {
    
    public static UserDetails buildUserDetails(){
        return User.withUsername("bestuser")
                .password("bestpassword")
                .roles("USER")
                .build();
    }
}
