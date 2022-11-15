package com.example.multiplicationservice.service;

import com.example.operation.aspects.OperationLog;
import com.example.operation.domain.Operation;
import org.springframework.stereotype.Service;

@Service
public class MultiplicationService implements CalculatorServiceInterface{
    @Override
    @OperationLog
    public Operation calculate(Double firstParameter, Double secondParameter) {
        return new Operation(Operation.Type.MULT, firstParameter, secondParameter,
                firstParameter * secondParameter);
    }
}
