package com.example.springmvccalcuclator.aspects;

import com.example.springmvccalcuclator.data.OperationsRepository;
import com.example.springmvccalcuclator.domain.Operation;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OperationAspect {
    private final OperationsRepository repository;

    @Autowired
    public OperationAspect(OperationsRepository repository) {
        this.repository = repository;
    }

    @AfterReturning(pointcut = "@annotation(OperationLog))", returning = "operation")
    public void saveReturnedOperation(Operation operation) {
        repository.save(operation);
    }
}
