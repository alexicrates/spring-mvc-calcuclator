package com.example.subtractionservice.controller;

import com.example.operation.domain.Operation;
import com.example.subtractionservice.service.CalculatorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubtractionController {

    private final CalculatorServiceInterface subtractionService;

    @Autowired
    public SubtractionController(CalculatorServiceInterface subtractionService) {
        this.subtractionService = subtractionService;
    }

    @GetMapping(value = "/SUB", params = {"a", "b"})
    public Operation subtract(@RequestParam("a") Double a,
                                 @RequestParam("b") Double b) {

        return subtractionService.calculate(a, b);
    }
}
