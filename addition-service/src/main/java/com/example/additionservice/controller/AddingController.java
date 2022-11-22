package com.example.additionservice.controller;

import com.example.additionservice.service.AddingService;
import com.example.operation.domain.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddingController {

    private final AddingService addingService;

    @Autowired
    public AddingController(AddingService addingService) {
        this.addingService = addingService;
    }

    @GetMapping(value = "/SUM", params = {"a", "b"})
    public Operation add(@RequestParam("a") Double a,
                         @RequestParam("b") Double b) {
        return addingService.calculate(a, b);
    }
}
