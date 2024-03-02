package com.practice.microservice.calculator.service;

import com.practice.microservice.calculator.repository.MultiplicationRepository;
import com.practice.microservice.calculator.service.model.Result;
import com.practice.microservice.calculator.service.remote.RoundClient;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MultiplicationService {

    public static final String OPERATION = "MULTIPLICATION";

    private final MultiplicationRepository repository;
    private final RoundClient roundClient;

    public MultiplicationService(MultiplicationRepository repository, RoundClient roundClient) {
        this.repository = repository;
        this.roundClient = roundClient;
    }

    public Result doMultiplication(double x, double y) {
        var result = new Result();
        result.setX(x);
        result.setY(y);
        result.setOperation(OPERATION);
        var multiplication = x * y;
        result.setValue(roundClient.doRound(multiplication));
        // Save the result in the database
        log.info("save operation of type:{} for x={} and y={}", OPERATION, x, y);
        return repository.save(result);
    }

    public List<Result> getMultiplications() {
        return repository.findByOperation(OPERATION);
    }
}
