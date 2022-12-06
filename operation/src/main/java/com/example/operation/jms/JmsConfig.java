package com.example.operation.jms;

import com.example.operation.domain.Operation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.Map;

@Configuration
public class JmsConfig {
    @Bean
    public MappingJackson2MessageConverter messageConverter(){
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("type");
        messageConverter.setTypeIdMappings(Map.of("operation", Operation.class));
        messageConverter.setTargetType(MessageType.TEXT);

        return messageConverter;
    }

}
