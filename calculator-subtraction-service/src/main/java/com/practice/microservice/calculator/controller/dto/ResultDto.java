package com.practice.microservice.calculator.controller.dto;

import lombok.Data;

@Data
public class ResultDto {

    private double x;
    private double y;
    private String operation;
    private double value;
}
