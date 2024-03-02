package com.practice.microservice.calculator.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.microservice.calculator.controller.dto.ParameterDto;
import com.practice.microservice.calculator.controller.dto.ResultDto;
import com.practice.microservice.calculator.service.model.Result;
import com.practice.microservice.calculator.service.MultiplicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/multiplication")
public class MultiplicationController {

    private final MultiplicationService service;

    @PostMapping
    public ResponseEntity<ResultDto> doMultiplication(@RequestBody ParameterDto parameterDto) {
        var result = service.doMultiplication(parameterDto.getX(), parameterDto.getY());
        var resultDto = mapModelToDto(result);
        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    @GetMapping
   public ResponseEntity<List<ResultDto>> getMultiplications() {
        var additionsDto = service.getMultiplications()
            .stream()
            .map(this::mapModelToDto)
            .toList();
        return ResponseEntity.ok().body(additionsDto);
   }

    private ResultDto mapModelToDto(Result result) {
        var resultDto = new ResultDto();
        resultDto.setX(result.getX());
        resultDto.setY(result.getY());
        resultDto.setOperation(result.getOperation());
        resultDto.setValue(result.getValue());
        return resultDto;
    }
}
