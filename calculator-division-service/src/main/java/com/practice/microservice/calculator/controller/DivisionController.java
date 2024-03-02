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
import com.practice.microservice.calculator.service.DivisionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/division")
public class DivisionController {

    private final DivisionService service;

    @PostMapping
    public ResponseEntity<ResultDto> doDivision(@RequestBody ParameterDto parameterDto) {
        var result = service.doDivision(parameterDto.getX(), parameterDto.getY());
        var resultDto = mapModelToDto(result);
        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    @GetMapping
   public ResponseEntity<List<ResultDto>> getDivisions() {
        var divisionsDto = service.getDivisions()
            .stream()
            .map(this::mapModelToDto)
            .toList();
        return ResponseEntity.ok().body(divisionsDto);
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
