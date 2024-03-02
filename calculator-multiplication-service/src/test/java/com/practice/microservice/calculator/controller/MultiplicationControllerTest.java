package com.practice.microservice.calculator.controller;

import static com.practice.microservice.calculator.TestUtil.createResult;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.microservice.calculator.controller.dto.ParameterDto;
import com.practice.microservice.calculator.service.model.Result;
import com.practice.microservice.calculator.service.MultiplicationService;


@WebMvcTest(MultiplicationController.class)
class MultiplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MultiplicationService service;

    @Test
    void testDoMultiplication() throws Exception {
        double x = 10;
        double y = 15;
        var parameterDto = new ParameterDto();
        parameterDto.setX(x);
        parameterDto.setY(y);

        when(service.doMultiplication(anyDouble(), anyDouble())).thenReturn(createResult(x, y, MultiplicationService.OPERATION));
        mockMvc.perform(post("/api/multiplication")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parameterDto)))
            .andExpectAll(
                status().isOk(),
                jsonPath("$.operation", is(MultiplicationService.OPERATION)),
                jsonPath("$.value", is(150.0))
            );
    }

    @Test
    void testGetMultiplications() throws Exception {
        when(service.getMultiplications()).thenReturn(Lists.list(new Result()));
        mockMvc.perform(get("/api/multiplication"))
            .andExpect(
                status().isOk()
            );
    }
}