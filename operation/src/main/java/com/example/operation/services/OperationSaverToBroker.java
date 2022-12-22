package com.example.operation.services;

import com.example.operation.domain.Operation;
import com.example.operation.event.OperationSentPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Profile("prod")
@Component
public class OperationSaverToBroker implements OperationSaver{

    private final JmsTemplate jmsTemplate;
    private final OperationSentPublisher publisher;

    @Autowired
    public OperationSaverToBroker(JmsTemplate jmsTemplate, OperationSentPublisher publisher) {
        this.jmsTemplate = jmsTemplate;
        this.publisher = publisher;
    }

    @Override
    @CachePut(value = "operations")
    public Operation saveOperation(Operation operation) {
        jmsTemplate.convertAndSend(operation);
        publisher.publishOperationSentEvent(operation);
        return operation;
    }
}
