package com.practice.microservice.calculator;

import com.practice.microservice.calculator.service.model.Result;

public class TestUtil {

    public static Result createResult(double X, double Y, String operation) {
        var result = new Result();
        result.setX(X);
        result.setY(Y);
        result.setOperation(operation);
        var value = X - Y;
        result.setValue(value);
        return result;
    }

}
