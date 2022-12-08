package com.example.operation.event;

import com.example.operation.domain.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OperationSentPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public OperationSentPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishOperationSentEvent(Operation operation) {
        OperationSentEvent customSpringEvent = new OperationSentEvent(operation);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
