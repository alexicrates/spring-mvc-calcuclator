package com.example.operationsaver.event;

import com.example.operation.domain.Operation;

public class OperationSavedEvent {
    private final Operation operation;

    public OperationSavedEvent(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }
}
