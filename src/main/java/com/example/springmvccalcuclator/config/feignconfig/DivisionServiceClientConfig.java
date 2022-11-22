package com.example.springmvccalcuclator.config.feignconfig;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class DivisionServiceClientConfig {
    @Bean
    public ErrorDecoder errorDecoder(){
        return new CustomDivisionDecoder();
    }
}
