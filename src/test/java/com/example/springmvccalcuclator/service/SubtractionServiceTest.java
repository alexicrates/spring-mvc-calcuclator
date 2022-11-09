package com.example.springmvccalcuclator.service;

import com.example.springmvccalcuclator.domain.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Subtraction service test")
public class SubtractionServiceTest {
    CalculatorServiceInterface subtractionService = new SubtractionService();

    @Test
    @DisplayName("Returned operation should be equal to the expected one")
    void returnedOperationEqualsToTheExpectedOne() {
        Operation operation = subtractionService.calculate(3.0, 2.0);

        Assertions.assertEquals(operation.getType(), Operation.Type.SUB);
        Assertions.assertEquals(3.0, operation.getFirstParameter());
        Assertions.assertEquals(2.0, operation.getSecondParameter());
        Assertions.assertEquals(1.0, operation.getResult());
    }
}
