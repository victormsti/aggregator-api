package com.example.aggregator.api.controller;

import com.example.aggregator.api.base.AbstractTest;
import com.example.aggregator.api.configuration.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ContactControllerIT extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void whenCallMethodGetContactsThenReturnOkStatusWithCorrectResponse() throws Exception {
        String validToken = jwtUtil.generateToken(expectedUsername);

        mockMvc.perform(get("/api/v1/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contacts").exists());
    }

    @Test
    public void whenCallMethodGetContactsThenReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/contacts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.contacts").doesNotExist());
    }

    @Test
    public void whenCallMethodGetContactByIdThenReturnOkStatusWithCorrectResponse() throws Exception {
        String validToken = jwtUtil.generateToken(expectedUsername);

        mockMvc.perform(get("/api/v1/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists());
    }

    @Test
    public void whenCallMethodGetContactByIdThenReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.id").doesNotExist());
    }

}
