package com.example.operationsaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class OperationSaverApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationSaverApplication.class, args);
    }

}
