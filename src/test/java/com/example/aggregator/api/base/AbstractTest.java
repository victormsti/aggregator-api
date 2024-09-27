package com.example.aggregator.api.base;

import com.example.aggregator.api.builder.ContactBuilder;
import com.example.aggregator.api.builder.UserDetailsBuilder;
import com.example.aggregator.api.client.response.ContactIntegrationBaseResponse;
import com.example.aggregator.api.controller.response.contact.ContactBaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AbstractTest {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected final String expectedUsername = "bestuser";
    protected final String expectedPassword = "bestpassword";
    protected final String expectedValidToken = "validToken";


    protected final ContactBaseResponse expectedContactBaseResponse = ContactBuilder.buildContactBaseResponse();
    protected final ContactIntegrationBaseResponse expectedContactIntegrationBaseResponse = ContactBuilder.buildContactIntegrationBaseResponse();
    protected final ContactIntegrationBaseResponse expectedEmptyContactIntegrationBaseResponse = ContactBuilder.buildEmptyContactIntegrationBaseResponse();
    protected final UserDetails expectedUserDetails = UserDetailsBuilder.buildUserDetails();
}
