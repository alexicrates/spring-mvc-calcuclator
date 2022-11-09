package com.example.springmvccalcuclator.service;

import com.example.springmvccalcuclator.aspects.OperationLog;
import com.example.springmvccalcuclator.domain.Operation;
import org.springframework.stereotype.Service;

@Service
public class AddingService implements CalculatorServiceInterface{
    @Override
    @OperationLog
    public Operation calculate(Double firstParameter, Double secondParameter) {
        return new Operation(Operation.Type.SUM, firstParameter, secondParameter,
                firstParameter + secondParameter);
    }
}
