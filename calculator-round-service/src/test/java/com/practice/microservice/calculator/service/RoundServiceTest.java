package com.practice.microservice.calculator.service;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoundServiceTest implements WithAssertions {

    private RoundService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RoundService();
    }

    @Test
    void testDoRound() {
        long expectedResult = 12;
        double actualValue = 12.45;
        assertThat(underTest.doRound(actualValue)).isEqualTo(expectedResult);
    }
}