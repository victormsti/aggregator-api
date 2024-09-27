package com.example.aggregator.api.builder;

import com.example.aggregator.api.client.response.ContactIntegrationBaseResponse;
import com.example.aggregator.api.client.response.ContactIntegrationResponse;
import com.example.aggregator.api.controller.response.contact.ContactBaseResponse;
import com.example.aggregator.api.controller.response.contact.ContactResponse;

import java.time.LocalDateTime;
import java.util.Collections;

public class ContactBuilder {

    public static ContactBaseResponse buildContactBaseResponse(){
        return ContactBaseResponse.builder()
                .contacts(Collections.singletonList(buildContactResponse()))
                .build();
    }

    public static ContactResponse buildContactResponse(){
        return ContactResponse.builder()
                .id(1)
                .email("test@test.com")
                .name("contactTest")
                .created_at(LocalDateTime.now()).build();
    }

    public static ContactIntegrationBaseResponse buildEmptyContactIntegrationBaseResponse(){
        return ContactIntegrationBaseResponse.builder()
                .contacts(Collections.emptyList())
                .build();
    }

    public static ContactIntegrationBaseResponse buildContactIntegrationBaseResponse(){
        return ContactIntegrationBaseResponse.builder()
                .contacts(Collections.singletonList(buildContactIntegrationResponse()))
                .build();
    }

    public static ContactIntegrationResponse buildContactIntegrationResponse(){
        return ContactIntegrationResponse.builder()
                .id(1)
                .email("test@test.com")
                .name("contactTest")
                .created_at(LocalDateTime.now()).build();
    }
}
