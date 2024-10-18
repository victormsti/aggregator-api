package com.example.aggregator.api.controller.mapper.impl.contact;

import com.example.aggregator.api.client.response.ContactIntegrationResponse;
import com.example.aggregator.api.controller.mapper.contract.contact.ContactMapper;
import com.example.aggregator.api.controller.response.contact.ContactBaseResponse;
import com.example.aggregator.api.controller.response.contact.ContactResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactMapperImpl implements ContactMapper {
    public static final String KINECT_LABS_SOURCE = "KINECT_LABS";

    @Override
    public ContactBaseResponse toResponse(List<ContactIntegrationResponse> response) {
        List<ContactResponse> contacts = response.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ContactBaseResponse.builder()
                .contacts(contacts)
                .build();
    }

    @Override
    public ContactResponse toResponse(ContactIntegrationResponse response) {
        return ContactResponse.builder()
                .id(response.getId())
                .name(response.getName())
                .email(response.getEmail())
                .source(KINECT_LABS_SOURCE)
                .created_at(response.getCreated_at())
                .updated_at(response.getUpdated_at())
                .build();
    }
}
