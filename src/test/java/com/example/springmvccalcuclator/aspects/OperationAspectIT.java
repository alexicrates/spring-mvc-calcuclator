package com.example.springmvccalcuclator.aspects;

import com.example.springmvccalcuclator.data.OperationsRepository;
import com.example.springmvccalcuclator.domain.Operation;
import com.example.springmvccalcuclator.service.SubtractionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class OperationAspectIT {

    @Autowired
    private SubtractionService subtractionService;
    @MockBean
    private OperationsRepository operationsRepository;

    @Test
    void operationAspectShouldCallRepositorySaveMethod() {
        Operation operation = subtractionService.calculate(5.0, 1.0);

        Assertions.assertNotNull(operation);
        Mockito.verify(operationsRepository, Mockito.times(1)).save(operation);
    }
}
