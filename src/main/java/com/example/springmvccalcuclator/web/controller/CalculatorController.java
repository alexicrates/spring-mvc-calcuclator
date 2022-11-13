package com.example.springmvccalcuclator.web.controller;

import com.example.springmvccalcuclator.exeptions.DivisionByZeroException;
import com.example.springmvccalcuclator.repository.OperationsRepository;
import com.example.springmvccalcuclator.domain.Operation;
import com.example.springmvccalcuclator.service.CalculatorServiceInterface;
import com.example.springmvccalcuclator.web.dto.OperationDto;
import com.example.springmvccalcuclator.web.dto.OperationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping(path = "/calculator")
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorServiceInterface addingService;
    private final CalculatorServiceInterface subtractionService;
    private final CalculatorServiceInterface multiplicationService;
    private final CalculatorServiceInterface divisionService;
    private final OperationsRepository repository;
    private final OperationDtoMapper simpleMapper;

    @GetMapping(value = "/SUM", params = {"a", "b"})
    public OperationDto add(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        Operation operation = addingService.calculate(a, b);
        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/SUB", params = {"a", "b"})
    public OperationDto subtract(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        Operation operation = subtractionService.calculate(a, b);
        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/MULT", params = {"a", "b"})
    public OperationDto multiply(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        Operation operation = multiplicationService.calculate(a, b);
        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/DIV", params = {"a", "b"})
    public OperationDto divide(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        Operation operation = divisionService.calculate(a, b);
        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/pop", params = {"from", "to"})
    public Operation.Type pop(@RequestParam("from") @DateTimeFormat(pattern = "dd/MM/yyyy") Long from,
                              @RequestParam("to") @DateTimeFormat(pattern = "dd/MM/yyyy") Long to){
        return repository.
                findMostPopularWithinATimePeriod(new Timestamp(from), new Timestamp(to)).
                orElse(null);
    }

    @ExceptionHandler({DivisionByZeroException.class})
    public ResponseEntity<Object> handleDivisionByZeroExceptions(DivisionByZeroException exception) {
        return new ResponseEntity<>("400 Bad request\nDivision by zero (((", HttpStatus.BAD_REQUEST);
    }
}
