package com.example.aggregator.api.service.contract;

import com.example.aggregator.api.client.response.ContactIntegrationResponse;
import com.example.aggregator.api.controller.response.contact.ContactBaseResponse;
import com.example.aggregator.api.controller.response.contact.ContactResponse;

import java.util.List;

public interface ContactService {

    ContactBaseResponse getContacts();
    ContactResponse getContactById(Integer id);
}
