package com.example.springmvccalcuclator.web.controller;

import com.example.operation.domain.Operation;
import com.example.operation.repository.OperationsRepository;
import com.example.springmvccalcuclator.exeptions.DivisionByZeroException;
import com.example.springmvccalcuclator.web.dto.OperationDto;
import com.example.springmvccalcuclator.web.dto.OperationDtoMapper;
import com.example.springmvccalcuclator.web.feignclients.AdditionServiceClient;
import com.example.springmvccalcuclator.web.feignclients.DivisionServiceClient;
import com.example.springmvccalcuclator.web.feignclients.MultiplicationServiceClient;
import com.example.springmvccalcuclator.web.feignclients.SubtractionServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping(path = "/calculator")
@AllArgsConstructor
public class CalculatorController {
    private final OperationsRepository repository;
    private final OperationDtoMapper simpleMapper;

    private final AdditionServiceClient additionServiceClient;
    private final SubtractionServiceClient subtractionServiceClient;
    private final MultiplicationServiceClient multiplicationServiceClient;
    private final DivisionServiceClient divisionServiceClient;

    @GetMapping(value = "/SUM", params = {"a", "b"})
    public OperationDto add(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        Operation operation = additionServiceClient.gerOperation(a, b);
        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/SUB", params = {"a", "b"})
    public OperationDto subtract(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        Operation operation = subtractionServiceClient.gerOperation(a, b);
        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/MULT", params = {"a", "b"})
    public OperationDto multiply(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        Operation operation = multiplicationServiceClient.gerOperation(a, b);
        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/DIV", params = {"a", "b"})
    public OperationDto divide(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        Operation operation = divisionServiceClient.gerOperation(a, b);
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
    public ResponseEntity<Object> handleDivisionByZeroExceptions() {
        return new ResponseEntity<>("400 Bad request\nDivision by zero (((", HttpStatus.BAD_REQUEST);
    }
}
