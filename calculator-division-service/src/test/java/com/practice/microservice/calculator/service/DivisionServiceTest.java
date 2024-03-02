package com.practice.microservice.calculator.service;

import static com.practice.microservice.calculator.TestUtil.createResult;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

import com.practice.microservice.calculator.service.remote.RoundClient;
import org.assertj.core.api.WithAssertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.practice.microservice.calculator.service.model.Result;
import com.practice.microservice.calculator.repository.DivisionRepository;

@ExtendWith(MockitoExtension.class)
class DivisionServiceTest implements WithAssertions {

    private final double x = 20;
    private final double y = 5;
    @Mock
    private DivisionRepository repository;
    @Mock
    private RoundClient roundClient;
    @InjectMocks
    private DivisionService underTest;

    @Test
    void testDoDivision() {
        when(repository.save(any(Result.class))).thenReturn(createResult(x, y, DivisionService.OPERATION));
        when(roundClient.doRound(anyDouble())).thenReturn(Long.valueOf(25));
        var result = underTest.doDivision(x, y);
        assertAll(
            () -> assertThat(result.getOperation()).isEqualTo(DivisionService.OPERATION),
            () -> assertThat(result.getValue()).isEqualTo(x / y)
        );
    }

    @Test
    void testDoDivision_throwException() {
        var exception = assertThrows(ArithmeticException.class, () -> underTest.doDivision(x, 0));
        assertThat(exception.getMessage()).contains("perform a division by 0");
    }

    @Test
    void testGetDivisions() {
        when(repository.findByOperation(DivisionService.OPERATION)).thenReturn(Lists.list(new Result()));
        var results = underTest.getDivisions();
        assertThat(results).isNotEmpty();
    }
}