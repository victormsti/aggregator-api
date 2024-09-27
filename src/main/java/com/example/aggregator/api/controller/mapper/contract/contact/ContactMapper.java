package com.example.aggregator.api.controller.mapper.contract.contact;

import com.example.aggregator.api.client.response.ContactIntegrationResponse;
import com.example.aggregator.api.controller.response.contact.ContactBaseResponse;
import com.example.aggregator.api.controller.response.contact.ContactResponse;

import java.util.List;

public interface ContactMapper {

    ContactBaseResponse toResponse (List<ContactIntegrationResponse> response);
    ContactResponse toResponse (ContactIntegrationResponse response);
}
