package com.example.springmvccalcuclator.web.dto;

import com.example.operation.domain.Operation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationDtoMapper {
    OperationDto operationToOperationDto(Operation operation);
    Operation operationDtoToOperation(OperationDto operationDto);
}
