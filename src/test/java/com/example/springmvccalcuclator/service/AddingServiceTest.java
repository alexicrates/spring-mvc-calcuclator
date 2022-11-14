package com.example.springmvccalcuclator.service;

import com.example.operation.domain.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddingServiceTest {
    CalculatorServiceInterface addingService = new AddingService();

    @Test
    @DisplayName("Returned operation should be equal to the expected one")
    void returnedOperationShouldBeEqualToTheExpectedOne() {
        Operation operation = addingService.calculate(1.0, 2.0);
        Assertions.assertEquals(operation.getType(), Operation.Type.SUM);
        Assertions.assertEquals(1.0, operation.getFirstParameter());
        Assertions.assertEquals(2.0, operation.getSecondParameter());
        Assertions.assertEquals(3.0, operation.getResult());
    }
}
