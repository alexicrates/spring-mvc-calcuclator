package com.example.springmvccalcuclator.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "service.name")
@Getter
@Setter
public class ServiceNameConfigProperties {
    private String addition;
    private String subtraction;
    private String multiplication;
    private String division;
}
