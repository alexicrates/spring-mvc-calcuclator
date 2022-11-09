package com.example.springmvccalcuclator.service;

import com.example.springmvccalcuclator.domain.Operation;

public interface CalculatorServiceInterface {
    Operation calculate(Double firstParameter,
                        Double secondParameter);
}
