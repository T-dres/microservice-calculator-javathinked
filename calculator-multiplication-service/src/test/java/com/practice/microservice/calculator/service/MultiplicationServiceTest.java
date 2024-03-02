package com.practice.microservice.calculator.service;

import static com.practice.microservice.calculator.TestUtil.createResult;
import static org.junit.jupiter.api.Assertions.assertAll;
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
import com.practice.microservice.calculator.repository.MultiplicationRepository;

@ExtendWith(MockitoExtension.class)
class MultiplicationServiceTest implements WithAssertions {

    private final double x = 10;
    private final double y = 15;
    @Mock
    private MultiplicationRepository repository;
    @Mock
    private RoundClient roundClient;
    @InjectMocks
    private MultiplicationService underTest;

    @Test
    void testDoMultiplication() {
        when(repository.save(any(Result.class))).thenReturn(createResult(x, y, MultiplicationService.OPERATION));
        when(roundClient.doRound(anyDouble())).thenReturn(Long.valueOf(25));
        var result = underTest.doMultiplication(x, y);
        assertAll(
            () -> assertThat(result.getOperation()).isEqualTo(MultiplicationService.OPERATION),
            () -> assertThat(result.getValue()).isEqualTo(x * y)
        );
    }

    @Test
    void testGetMultiplications() {
        when(repository.findByOperation(MultiplicationService.OPERATION)).thenReturn(Lists.list(new Result()));
        var results = underTest.getMultiplications();
        assertThat(results).isNotEmpty();
    }
}