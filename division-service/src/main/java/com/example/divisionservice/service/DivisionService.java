package com.example.divisionservice.service;

import com.example.divisionservice.exeptions.DivisionByZeroException;
import com.example.operation.aspects.OperationLog;
import com.example.operation.domain.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
@RefreshScope
public class DivisionService {

    @Value("${precision}")
    private int precision;

    @OperationLog
    public Operation calculate(Double firstParameter, Double secondParameter) {
        if (secondParameter == 0)
            throw new DivisionByZeroException();

        String formattedResult = new DecimalFormat("#." + "0".repeat(precision)).
                format(firstParameter / secondParameter);
        String formattedFirstParameter = new DecimalFormat("#." + "0".repeat(precision)).
                format(firstParameter);
        String formattedSecondParameter = new DecimalFormat("#." + "0".repeat(precision)).
                format(secondParameter);

        return new Operation(Operation.Type.DIV,
                            Double.valueOf(formattedFirstParameter),
                            Double.valueOf(formattedSecondParameter),
                            Double.valueOf(formattedResult));
    }
}
