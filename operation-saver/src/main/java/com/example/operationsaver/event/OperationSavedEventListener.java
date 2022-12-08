package com.example.operationsaver.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OperationSavedEventListener {

    @EventListener
    public void handle(OperationSavedEvent operationSavedEvent){
        System.out.println(operationSavedEvent.getOperation() + " is saved");
    }
}
