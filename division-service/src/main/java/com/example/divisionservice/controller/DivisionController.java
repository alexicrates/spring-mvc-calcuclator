package com.example.divisionservice.controller;

import com.example.divisionservice.exeptions.DivisionByZeroException;
import com.example.divisionservice.service.DivisionService;
import com.example.operation.domain.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DivisionController {

    private final DivisionService divisionService;

    @Autowired
    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @GetMapping(value = "/DIV", params = {"a", "b"})
    public Operation divide(@RequestParam("a") Double a,
                            @RequestParam("b") Double b) {

        return divisionService.calculate(a, b);
    }

    @ExceptionHandler({DivisionByZeroException.class})
    public ResponseEntity<Object> handleDivisionByZeroExceptions() {
        return new ResponseEntity<>("400 Bad request\nDivision by zero (((", HttpStatus.BAD_REQUEST);
    }
}
