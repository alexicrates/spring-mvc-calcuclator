package com.example.springmvccalcuclator.aspects;

import com.example.springmvccalcuclator.data.OperationsRepository;
import com.example.springmvccalcuclator.domain.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OperationAspectTest {

    @Mock
    OperationsRepository repository;
    Operation operation;

    @InjectMocks
    OperationAspect aspect;

    @Test
    @DisplayName("Repository save method should be called")
    void repositorySaveMethodShouldBeCalled() {
        operation = new Operation(Operation.Type.SUM, 1.0, 2.0, 3.0);
        aspect.saveReturnedOperation(operation);
        Mockito.verify(repository, times(1)).save(operation);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when argument is null")
    void shouldThrowIllegalArgumentExceptionWhenArgumentIsNull(){
        when(repository.save(null)).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> aspect.saveReturnedOperation(null));
    }
}
