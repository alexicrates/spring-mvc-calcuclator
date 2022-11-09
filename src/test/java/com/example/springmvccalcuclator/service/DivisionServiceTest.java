package com.example.springmvccalcuclator.service;

import com.example.springmvccalcuclator.DivisionByZeroException;
import com.example.springmvccalcuclator.domain.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DivisionServiceTest {
    CalculatorServiceInterface divisionService = new DivisionService();

    @Test
    @DisplayName("Returned operation should be equal to the expected one")
    void returnedOperationShouldBeEqualToTheExpectedOne() {
        Operation operation = divisionService.calculate(6.0, 2.0);

        Assertions.assertEquals(operation.getType(), Operation.Type.DIV);
        Assertions.assertEquals(6.0, operation.getFirstParameter());
        Assertions.assertEquals(2.0, operation.getSecondParameter());
        Assertions.assertEquals(3.0, operation.getResult());
    }

    @Test
    @DisplayName("Should throw DivisionByZeroException")
    void shouldThrowDivisionByZeroException(){
        Assertions.assertThrows(DivisionByZeroException.class,
                () -> divisionService.calculate(2.0, 0.0));
    }
}
