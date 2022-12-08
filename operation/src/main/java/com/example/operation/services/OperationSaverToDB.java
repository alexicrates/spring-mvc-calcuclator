package com.example.operation.services;

import com.example.operation.domain.Operation;
import com.example.operation.event.OperationSentPublisher;
import com.example.operation.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class OperationSaverToDB implements OperationSaver{

    private final OperationsRepository repository;
    private final OperationSentPublisher publisher;

    @Autowired
    public OperationSaverToDB(OperationsRepository repository, OperationSentPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Override
    public void saveOperation(Operation operation) {
        repository.save(operation);
        publisher.publishOperationSentEvent(operation);
    }
}
