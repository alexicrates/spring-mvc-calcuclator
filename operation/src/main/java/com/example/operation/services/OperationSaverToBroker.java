package com.example.operation.services;

import com.example.operation.domain.Operation;
import com.example.operation.event.OperationSentPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Profile("prod")
@Component
public class OperationSaverToBroker implements OperationSaver{

    private final JmsTemplate jmsTemplate;
    private final OperationSentPublisher publisher;
    private final CacheManager cacheManager;

    @Autowired
    public OperationSaverToBroker(JmsTemplate jmsTemplate, OperationSentPublisher publisher, CacheManager cacheManager) {
        this.jmsTemplate = jmsTemplate;
        this.publisher = publisher;
        this.cacheManager = cacheManager;
    }

    @Override
    @CachePut(value = "operations")
//    @CacheEvict(value = "operations", condition = "#root.caches.size()<1", allEntries = true)
    public Operation saveOperation(Operation operation) {
        jmsTemplate.convertAndSend(operation);
        publisher.publishOperationSentEvent(operation);
        return operation;
    }
}
