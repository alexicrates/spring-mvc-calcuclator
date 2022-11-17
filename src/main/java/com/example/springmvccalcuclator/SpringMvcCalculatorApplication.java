package com.example.springmvccalcuclator;

import com.example.springmvccalcuclator.properties.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class SpringMvcCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcCalculatorApplication.class, args);
    }

}
