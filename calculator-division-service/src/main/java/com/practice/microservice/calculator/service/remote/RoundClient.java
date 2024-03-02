package com.practice.microservice.calculator.service.remote;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface RoundClient {

    @GetExchange("/api/round/{value}")
    Long doRound(@PathVariable double value);
}
