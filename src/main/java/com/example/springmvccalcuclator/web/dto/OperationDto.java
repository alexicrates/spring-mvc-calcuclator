package com.example.springmvccalcuclator.web.dto;

import com.example.springmvccalcuclator.domain.Operation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;

@Getter
@Setter
public class OperationDto implements Serializable {
    private Calendar createdAt;
    private Operation.Type type;
    private Double firstParameter;
    private Double secondParameter;
    private Double result;

    public OperationDto() {
    }
}