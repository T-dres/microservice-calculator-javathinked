package com.practice.microservice.calculator;

import com.practice.microservice.calculator.service.model.Result;

public class TestUtil {

    public static Result createResult(double x, double y, String operation) {
        var result = new Result();
        result.setX(x);
        result.setY(y);
        result.setOperation(operation);
        var value = x + y;
        result.setValue(value);
        return result;
    }

}
