package com.practice.microservice.calculator.service;

import com.practice.microservice.calculator.repository.SubtractionRepository;
import com.practice.microservice.calculator.service.model.Result;
import com.practice.microservice.calculator.service.remote.RoundClient;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubtractionService {

    public static final String OPERATION = "SUBTRACTION";
    private final SubtractionRepository repository;
    private final RoundClient roundClient;

    public SubtractionService(SubtractionRepository repository, RoundClient roundClient) {
        this.repository = repository;
        this.roundClient = roundClient;
    }

    public Result doSubtraction(double x, double y) {
        var result = new Result();
        result.setX(x);
        result.setY(y);
        result.setOperation(OPERATION);
        var subtraction = x - y;
        result.setValue(roundClient.doRound(subtraction));
        // Save the result in the database
        log.info("save operation of type:{} for x={} and y={}", OPERATION, x, y);
        return repository.save(result);
    }

    public List<Result> getSubtractions() {
        return repository.findByOperation(OPERATION);
    }
}
