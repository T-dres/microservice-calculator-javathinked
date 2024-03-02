package com.practice.microservice.calculator.controller;

import com.practice.microservice.calculator.controller.dto.ParameterDto;
import com.practice.microservice.calculator.controller.dto.ResultDto;
import com.practice.microservice.calculator.service.AdditionService;
import com.practice.microservice.calculator.service.model.Result;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/addition")
public class AdditionController {

    private final AdditionService service;

    @PostMapping
    public ResponseEntity<ResultDto> doAddition(@RequestBody ParameterDto parameterDto) {
        var result = service.doAddition(parameterDto.getX(), parameterDto.getY());
        var resultDto = mapModelToDto(result);
        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    @GetMapping
   public ResponseEntity<List<ResultDto>> getAdditions() {
        var additionsDto = service.getAdditions()
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
