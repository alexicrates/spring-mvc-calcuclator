package com.example.divisionservice.service;

import com.example.operation.domain.Operation;

public interface CalculatorServiceInterface {
    Operation calculate(Double firstParameter,
                        Double secondParameter);
}
