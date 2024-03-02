package com.practice.microservice.calculator.controller;

import com.practice.microservice.calculator.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/round")
public class RoundController {

    private final RoundService service;

    @GetMapping("/{value}")
    public Long doRound(@PathVariable double value) {
        return service.doRound(value);
    }
}
