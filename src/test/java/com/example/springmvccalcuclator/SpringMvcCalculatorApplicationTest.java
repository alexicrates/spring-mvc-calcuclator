package com.example.springmvccalcuclator;

import com.example.springmvccalcuclator.web.controller.CalculatorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SpringMvcCalculatorApplicationTest {

    @Autowired
    private CalculatorController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
