package com.example.operation.aspects;

import com.example.operation.domain.Operation;
import com.example.operation.services.OperationSaver;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OperationAspect {
    private final OperationSaver operationSaver;

    @Autowired
    public OperationAspect(OperationSaver operationSaver) {
        this.operationSaver = operationSaver;
    }

    @AfterReturning(pointcut = "@annotation(OperationLog))", returning = "operation")
    public void saveReturnedOperation(Operation operation) {
        operationSaver.saveOperation(operation);
    }
}
