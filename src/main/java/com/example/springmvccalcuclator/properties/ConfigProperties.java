package com.example.springmvccalcuclator.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "calculator.url")
@Getter
@Setter
public class ConfigProperties {
    private String addition;
    private String subtraction;
    private String multiplication;
    private String division;
}
