package com.example.springmvccalcuclator.web.controller;

import com.example.operation.domain.Operation;
import com.example.operation.repository.OperationsRepository;
import com.example.springmvccalcuclator.config.properties.ServiceNameConfigProperties;
import com.example.springmvccalcuclator.exeptions.DivisionByZeroException;
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
    private final ServiceNameConfigProperties serviceNameConfigProperties;
    private final WebClient.Builder webClientBuilder;

    @GetMapping(value = "/SUM", params = {"a", "b"})
    public OperationDto add(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        String additionServiceName = serviceNameConfigProperties.getAddition();

        Operation operation = webClientBuilder
                .build()
                .get()
                .uri("http://{name}/SUM?a={a}&b={b}", additionServiceName, a, b)
                .retrieve()
                .bodyToMono(Operation.class)
                .block();

        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/SUB", params = {"a", "b"})
    public OperationDto subtract(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        String subtractionHost = serviceNameConfigProperties.getSubtraction();

        Operation operation = webClientBuilder
                .build()
                .get()
                .uri("http://{name}/SUB?a={a}&b={b}", subtractionHost, a, b)
                .retrieve()
                .bodyToMono(Operation.class)
                .block();

        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/MULT", params = {"a", "b"})
    public OperationDto multiply(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        String multiplicationHost = serviceNameConfigProperties.getMultiplication();

        Operation operation = webClientBuilder
                .build()
                .get()
                .uri("http://{name}/MULT?a={a}&b={b}", multiplicationHost, a, b)
                .retrieve()
                .bodyToMono(Operation.class)
                .block();

        return simpleMapper.operationToOperationDto(operation);
    }

    @GetMapping(value = "/DIV", params = {"a", "b"})
    public OperationDto divide(@RequestParam("a") Double a,
                                  @RequestParam("b") Double b) {

        String divisionHost = serviceNameConfigProperties.getDivision();

        Operation operation = webClientBuilder
                .build()
                .get()
                .uri("http://{name}/DIV?a={a}&b={b}", divisionHost, a, b)
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
