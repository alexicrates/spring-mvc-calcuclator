package com.example.multiplicationservice.controller;

import com.example.multiplicationservice.service.MultiplicationService;
import com.example.operation.domain.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiplicationController {
    private final MultiplicationService multiplicationService;

    @Autowired
    public MultiplicationController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @GetMapping(value = "/MULT", params = {"a", "b"})
    public Operation multiply(@RequestParam("a") Double a,
                                 @RequestParam("b") Double b) {

        return multiplicationService.calculate(a, b);
    }
}
