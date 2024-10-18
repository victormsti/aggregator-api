package com.example.aggregator.api.service.impl;

import com.example.aggregator.api.client.response.ContactIntegrationResponse;
import com.example.aggregator.api.client.KenectLabsClient;
import com.example.aggregator.api.configuration.exception.NotFoundException;
import com.example.aggregator.api.controller.mapper.contract.contact.ContactMapper;
import com.example.aggregator.api.controller.response.contact.ContactBaseResponse;
import com.example.aggregator.api.controller.response.contact.ContactResponse;
import com.example.aggregator.api.service.contract.ContactService;
import feign.FeignException.InternalServerError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final KenectLabsClient client;
    private final ContactMapper contactMapper;

    public ContactServiceImpl(KenectLabsClient client,
                              ContactMapper contactMapper) {
        this.client = client;
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactBaseResponse getContacts() {
        List<ContactIntegrationResponse> totalContacts = new ArrayList<>();
        int currentPage = 1;
        boolean hasMoreData = true;

        while (hasMoreData) {
            try {
                List<ContactIntegrationResponse> response = client.getContacts(currentPage).getContacts();
                if (response.isEmpty()) {
                    hasMoreData = false;
                    continue;
                }

                totalContacts.addAll(response);
                currentPage++;
            } catch (InternalServerError ex) {
                return contactMapper.toResponse(totalContacts);
            } catch (Exception ex){
                log.error("An error occurred: {}: {}", currentPage, ex.getMessage());
            }
        }
        return contactMapper.toResponse(totalContacts);
    }

    @Override
    public ContactResponse getContactById(Integer id) {
        int currentPage = 1;
        boolean hasMoreData = true;

        while (hasMoreData) {
            try {
                List<ContactIntegrationResponse> contacts = client.getContacts(currentPage).getContacts();

                if (contacts.isEmpty()) {
                    hasMoreData = false;
                    continue;
                }

                Optional<ContactIntegrationResponse> contactOptional = contacts.stream()
                        .filter(contact -> contact.getId().equals(id))
                        .findFirst();

                if (contactOptional.isPresent()) {
                    return contactMapper.toResponse(contactOptional.get());
                }

                currentPage++;
            } catch (InternalServerError ex) {
                throw new NotFoundException(String.format("Contact not found by id: %s", id));
            } catch (Exception ex){
                log.error("An error occurred: {}: {}", currentPage, ex.getMessage());
            }
        }
        throw new NotFoundException(String.format("Contact not found by id: %s", id));
    }
}
