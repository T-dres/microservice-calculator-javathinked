package com.practice.microservice.calculator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoundService {

    public long doRound(double value) {
        log.info("round the value of: {}", value);
        return Math.round(value);
    }

}
