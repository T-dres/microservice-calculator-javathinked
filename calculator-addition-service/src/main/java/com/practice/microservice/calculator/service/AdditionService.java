package com.practice.microservice.calculator.service;

import com.practice.microservice.calculator.repository.AdditionRepository;
import com.practice.microservice.calculator.service.model.Result;
import com.practice.microservice.calculator.service.remote.RoundClient;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdditionService {

    public static final String OPERATION = "ADDITION";
    private final AdditionRepository repository;
    private final RoundClient roundClient;

    public AdditionService(AdditionRepository repository, RoundClient roundClient) {
        this.repository = repository;
        this.roundClient = roundClient;
    }

    public Result doAddition(double x, double y) {
        var result = new Result();
        result.setX(x);
        result.setY(y);
        result.setOperation(OPERATION);
        var addition = x + y;
        result.setValue(roundClient.doRound(addition));
        // Save the result in the database
        log.info("save operation of type:{} for x={} and y={}", OPERATION, x, y);
        return repository.save(result);
    }

    public List<Result> getAdditions() {
        return repository.findByOperation(OPERATION);
    }
}
