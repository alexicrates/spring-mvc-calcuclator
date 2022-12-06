package com.example.operation.aspects;

import com.example.operation.domain.Operation;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OperationAspect {
    private final JmsTemplate jmsTemplate;

    @Autowired
    public OperationAspect(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @AfterReturning(pointcut = "@annotation(OperationLog))", returning = "operation")
    public void saveReturnedOperation(Operation operation) {
        jmsTemplate.convertAndSend(operation);
    }
}
