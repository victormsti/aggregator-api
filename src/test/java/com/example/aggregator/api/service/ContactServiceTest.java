package com.example.aggregator.api.service;

import com.example.aggregator.api.base.AbstractTest;
import com.example.aggregator.api.client.KenectLabsClient;
import com.example.aggregator.api.configuration.exception.NotFoundException;
import com.example.aggregator.api.controller.mapper.contract.contact.ContactMapper;
import com.example.aggregator.api.controller.response.contact.ContactBaseResponse;
import com.example.aggregator.api.controller.response.contact.ContactResponse;
import com.example.aggregator.api.service.impl.ContactServiceImpl;
import feign.FeignException.InternalServerError;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ContactServiceTest extends AbstractTest {

    @Mock
    private KenectLabsClient client;

    @Mock
    private ContactMapper contactMapper;

    @InjectMocks
    private ContactServiceImpl contactService;

    @Test
    public void whenCallMethodGetContactsThenShouldReturnContactList(){
        when(client.getContacts(1)).thenReturn(expectedContactIntegrationBaseResponse);
        when(client.getContacts(2)).thenThrow(InternalServerError.class);

        when(contactMapper.toResponse(expectedContactIntegrationBaseResponse.getContacts())).thenReturn(expectedContactBaseResponse);

        ContactBaseResponse result = contactService.getContacts();

        assertEquals(expectedContactBaseResponse.getContacts().get(0).getId(), result.getContacts().get(0).getId());
        assertEquals(expectedContactBaseResponse.getContacts().get(0).getEmail(), result.getContacts().get(0).getEmail());
        assertEquals(expectedContactBaseResponse.getContacts().get(0).getName(), result.getContacts().get(0).getName());
        assertEquals(expectedContactBaseResponse.getContacts().get(0).getCreated_at(), result.getContacts().get(0).getCreated_at());
        assertEquals(expectedContactBaseResponse.getContacts().get(0).getUpdated_at(), result.getContacts().get(0).getUpdated_at());
    }

    @Test
    public void whenCallMethodGetContactByIdThenShouldReturnContact(){
        when(client.getContacts(1)).thenReturn(expectedContactIntegrationBaseResponse);

        when(contactMapper.toResponse(expectedContactIntegrationBaseResponse.getContacts().get(0)))
                .thenReturn(expectedContactBaseResponse.getContacts().get(0));

        ContactResponse result = contactService.getContactById(1);

        assertEquals(expectedContactBaseResponse.getContacts().get(0).getId(), result.getId());
        assertEquals(expectedContactBaseResponse.getContacts().get(0).getEmail(), result.getEmail());
        assertEquals(expectedContactBaseResponse.getContacts().get(0).getName(), result.getName());
        assertEquals(expectedContactBaseResponse.getContacts().get(0).getCreated_at(), result.getCreated_at());
        assertEquals(expectedContactBaseResponse.getContacts().get(0).getUpdated_at(), result.getUpdated_at());
    }

    @Test
    public void whenCallMethodGetContactByIdThenShouldThrowNotFoundExceptionWhenReachTheNonExistingPage(){
        when(client.getContacts(1)).thenReturn(expectedContactIntegrationBaseResponse);
        when(client.getContacts(2)).thenThrow(InternalServerError.class);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            contactService.getContactById(999);
        });

        assertEquals("Contact not found by id: 999", exception.getMessage());
    }

    @Test
    public void whenCallMethodGetContactByIdThenShouldThrowNotFoundExceptionWhenReturningAnEmptyList(){
        when(client.getContacts(1)).thenReturn(expectedContactIntegrationBaseResponse);
        when(client.getContacts(2)).thenReturn(expectedEmptyContactIntegrationBaseResponse);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            contactService.getContactById(999);
        });

        assertEquals("Contact not found by id: 999", exception.getMessage());
    }
}
