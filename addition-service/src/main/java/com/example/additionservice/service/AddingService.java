package com.example.additionservice.service;

import com.example.operation.aspects.OperationLog;
import com.example.operation.domain.Operation;
import com.example.operation.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddingService {
    @Autowired
    OperationsRepository operationsRepository;

    @OperationLog
    public Operation calculate(Double firstParameter, Double secondParameter) {
        Operation operation = new Operation(Operation.Type.SUM, firstParameter, secondParameter,
                firstParameter + secondParameter);
        operationsRepository.save(operation);
        return operation;
    }
}
