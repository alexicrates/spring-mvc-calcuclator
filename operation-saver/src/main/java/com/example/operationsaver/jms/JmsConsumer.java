package com.example.operationsaver.jms;

import com.example.operation.domain.Operation;
import com.example.operation.repository.OperationsRepository;
import com.example.operationsaver.event.OperationSavedEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class JmsConsumer {
    private final OperationsRepository repository;
    private final OperationSavedEventPublisher publisher;
    @Autowired
    public JmsConsumer(OperationsRepository repository, OperationSavedEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @JmsListener(destination = "operations.queue")
    @SendTo({"responses.queue"})
    public Operation receiveOperation(Operation operation) {
        Operation saved = repository.save(operation);
        publisher.publishOperationSavedEvent(saved);
        return saved;
    }


}