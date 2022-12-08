package com.example.operation.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class OperationSentListener {
    private final ConfigurableEnvironment environment;

    @Autowired
    public OperationSentListener(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @EventListener
    public void handle(OperationSentEvent operationSentEvent){
        boolean dev = Arrays.asList(environment.getActiveProfiles()).contains("dev");
        boolean prod = Arrays.asList(environment.getActiveProfiles()).contains("prod");

            if (dev){
                System.out.println(operationSentEvent.getOperation() + " is saved");
            }
            else if (prod){
                System.out.println(operationSentEvent.getOperation() + " is sent to message broker");
            }
    }

}
