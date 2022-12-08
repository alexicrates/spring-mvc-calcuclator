package com.example.operation.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@Entity(name = "operations")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Operation {

    @Id
    private UUID id;

    @Column(length = 5, nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private Double firstParameter;

    @Column(nullable = false)
    private Double secondParameter;

    @Column(nullable = false)
    private Double result;

    @Column(nullable = false)
    private Calendar createdAt;

    public Operation(Type type, Double firstParameter, Double secondParameter, Double result) {
        this.id = UUID.randomUUID();
        this.createdAt = Calendar.getInstance();
        this.type = type;
        this.firstParameter = firstParameter;
        this.secondParameter = secondParameter;
        this.result = result;
    }

    public enum Type {
        SUM, SUB, MULT, DIV
    }
}
