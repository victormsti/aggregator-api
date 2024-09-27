package com.example.aggregator.api.controller;

import com.example.aggregator.api.controller.response.contact.ContactBaseResponse;
import com.example.aggregator.api.controller.response.contact.ContactResponse;
import com.example.aggregator.api.service.contract.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<ContactBaseResponse> getContacts(){
        return ResponseEntity.ok().body(contactService.getContacts());
    }

    @GetMapping("{id}")
    public ResponseEntity<ContactResponse> getContactById(@PathVariable Integer id){
        return ResponseEntity.ok().body(contactService.getContactById(id));
    }
}
