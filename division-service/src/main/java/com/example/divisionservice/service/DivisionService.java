package com.example.divisionservice.service;

import com.example.divisionservice.exeptions.DivisionByZeroException;
import com.example.operation.aspects.OperationLog;
import com.example.operation.domain.Operation;
import org.springframework.stereotype.Service;

@Service
public class DivisionService {
    @OperationLog
    public Operation calculate(Double firstParameter, Double secondParameter) {
        if (secondParameter == 0)
            throw new DivisionByZeroException();
        return new Operation(Operation.Type.DIV, firstParameter, secondParameter,
                firstParameter / secondParameter);
    }
}
