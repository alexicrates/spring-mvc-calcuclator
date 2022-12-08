package com.example.operation.event;

import com.example.operation.domain.Operation;
import lombok.Getter;

@Getter
public class OperationSentEvent {
    private final Operation operation;

    public OperationSentEvent(Operation operation) {
        this.operation = operation;
    }
}
