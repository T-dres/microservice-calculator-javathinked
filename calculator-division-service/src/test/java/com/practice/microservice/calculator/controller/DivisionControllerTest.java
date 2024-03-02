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
import com.practice.microservice.calculator.service.DivisionService;


@WebMvcTest(DivisionController.class)
class DivisionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DivisionService service;

    @Test
    void testDoDivision() throws Exception {
        double x = 20;
        double y = 5;
        var parameterDto = new ParameterDto();
        parameterDto.setX(x);
        parameterDto.setY(y);

        when(service.doDivision(anyDouble(), anyDouble())).thenReturn(createResult(x, y, DivisionService.OPERATION));
        mockMvc.perform(post("/api/division")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parameterDto)))
            .andExpectAll(
                status().isOk(),
                jsonPath("$.operation", is(DivisionService.OPERATION)),
                jsonPath("$.value", is(4.0))
            );
    }

    @Test
    void testGetDivisions() throws Exception {
        when(service.getDivisions()).thenReturn(Lists.list(new Result()));
        mockMvc.perform(get("/api/division"))
            .andExpect(
                status().isOk()
            );
    }
}