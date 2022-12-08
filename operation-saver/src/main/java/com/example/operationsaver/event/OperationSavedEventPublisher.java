package com.example.operationsaver.event;

import com.example.operation.domain.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OperationSavedEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public OperationSavedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishOperationSavedEvent(Operation operation){
        OperationSavedEvent operationSavedEvent = new OperationSavedEvent(operation);
        applicationEventPublisher.publishEvent(operationSavedEvent);
    }
}
