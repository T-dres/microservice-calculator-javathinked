package com.practice.microservice.calculator.service;

import com.practice.microservice.calculator.repository.DivisionRepository;
import com.practice.microservice.calculator.service.model.Result;
import com.practice.microservice.calculator.service.remote.RoundClient;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DivisionService {

    public static final String OPERATION = "DIVISION";
    private final DivisionRepository repository;
    private final RoundClient roundClient;

    public DivisionService(DivisionRepository repository, RoundClient roundClient) {
        this.repository = repository;
        this.roundClient = roundClient;
    }

    public Result doDivision(double x, double y) {
        if(y == 0) {
            throw new ArithmeticException("Can't perform a division by 0");
        }
        var result = new Result();
        result.setX(x);
        result.setY(y);
        result.setOperation(OPERATION);
        var division = x / y;
        result.setValue(roundClient.doRound(division));
        // Save the result in the database
        log.info("save operation of type:{} for x={} and y={}", OPERATION, x, y);
        return repository.save(result);
    }

    public List<Result> getDivisions() {
        return repository.findByOperation(OPERATION);
    }
}
