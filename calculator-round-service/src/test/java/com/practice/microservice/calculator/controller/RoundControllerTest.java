package com.practice.microservice.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.microservice.calculator.service.RoundService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoundController.class)
class RoundControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoundService service;

    @Test
    void testDoRound() throws Exception {

        when(service.doRound(12.45)).thenReturn(12L);
        mockMvc.perform(get("/api/round/12.45"))
            .andExpectAll(
                status().isOk(),
                content().string("12")
            );
    }
}