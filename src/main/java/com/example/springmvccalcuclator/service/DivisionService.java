package com.example.springmvccalcuclator.service;

import com.example.springmvccalcuclator.exeptions.DivisionByZeroException;
import com.example.springmvccalcuclator.aspects.OperationLog;
import com.example.springmvccalcuclator.domain.Operation;
import org.springframework.stereotype.Service;

@Service
public class DivisionService implements CalculatorServiceInterface{
    @Override
    @OperationLog
    public Operation calculate(Double firstParameter, Double secondParameter) {
        if (secondParameter == 0)
            throw new DivisionByZeroException();
        return new Operation(Operation.Type.DIV, firstParameter, secondParameter,
                firstParameter / secondParameter);
    }
}
