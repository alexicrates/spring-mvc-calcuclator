package com.example.multiplicationservice.controller;

import com.example.multiplicationservice.service.CalculatorServiceInterface;
import com.example.operation.domain.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiplicationController {
    private final CalculatorServiceInterface multiplicationService;

    @Autowired
    public MultiplicationController(CalculatorServiceInterface multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @GetMapping(value = "/MULT", params = {"a", "b"})
    public Operation multiply(@RequestParam("a") Double a,
                                 @RequestParam("b") Double b) {

        return multiplicationService.calculate(a, b);
    }
}
