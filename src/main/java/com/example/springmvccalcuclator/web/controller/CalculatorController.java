package com.example.springmvccalcuclator.web.controller;

import com.example.operation.domain.Operation;
import com.example.operation.repository.OperationsRepository;
import com.example.springmvccalcuclator.exeptions.DivisionByZeroException;
import com.example.springmvccalcuclator.properties.ConfigProperties;
import com.example.springmvccalcuclator.web.dto.OperationDto;
import com.example.springmvccalcuclator.web.dto.OperationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;

@RestController
@RequestMapping(path = "/calculator")
@RequiredArgsConstructor
public class CalculatorController {
    private final OperationsRepository repository;
    private final OperationDtoMapper simpleMapper;
    private final WebClient webClient = WebClient.create();
    private final ConfigProperties configProperties;

    @GetMapping(value = "/SUM", params = {"a", "b"})
    public OperationDto add(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {
        String additionHost = configProperties.getAddition();
        Operation operation = webClient
                .get()
                .uri(additionHost + "/SUM?a={a}&b={b}", a, b)
                .retrieve()
                .bodyToMono(Operation.class)
                .block();

        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/SUB", params = {"a", "b"})
    public OperationDto subtract(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {
        String subtractionHost = configProperties.getSubtraction();
        Operation operation = webClient
                .get()
                .uri(subtractionHost + "/SUB?a={a}&b={b}", a, b)
                .retrieve()
                .bodyToMono(Operation.class)
                .block();

        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/MULT", params = {"a", "b"})
    public OperationDto multiply(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {
        String multiplicationHost = configProperties.getMultiplication();
        Operation operation = webClient
                .get()
                .uri(multiplicationHost + "/MULT?a={a}&b={b}", a, b)
                .retrieve()
                .bodyToMono(Operation.class)
                .block();

        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/DIV", params = {"a", "b"})
    public OperationDto divide(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {
        String divisionHost = configProperties.getDivision();
        Operation operation = webClient
                .get()
                .uri(divisionHost + "/DIV?a={a}&b={b}", a, b)
                .retrieve()
                .bodyToMono(Operation.class)
                .onErrorResume(e -> Mono.error(new DivisionByZeroException()))
                .block();

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
