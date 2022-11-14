package com.example.operation.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity(name = "operations")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"createdAt"})
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
