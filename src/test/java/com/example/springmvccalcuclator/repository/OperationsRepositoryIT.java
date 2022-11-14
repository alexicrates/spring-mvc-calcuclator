package com.example.springmvccalcuclator.repository;

import com.example.operation.domain.Operation;
import com.example.operation.repository.OperationsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@SpringBootTest
@Transactional
public class OperationsRepositoryIT {
    @Autowired
    private OperationsRepository repository;

//    @AfterEach
//    public void afterEach(){
//        repository.deleteAll();
//    }

    @Test
    @DisplayName("Operation is saved then found")
    public void operationIsSavedThenFound(){
        Operation saved = repository.save(
                new Operation(Operation.Type.SUM, 1.0, 2.0, 3.0));
        Operation findOperation = repository.findById(saved.getId()).orElse(null);
        
        Assertions.assertNotNull(findOperation);
        Assertions.assertEquals(saved, findOperation);
    }

    @Test
    @DisplayName("Should return most popular operation type")
    public void shouldReturnMostPopularOperationType(){
        repository.findAll().forEach(System.out::println);

        repository.save(new Operation(Operation.Type.SUM, 1.0, 2.0, 3.0));
        repository.save(new Operation(Operation.Type.DIV, 6.0, 2.0, 3.0));
        repository.save(new Operation(Operation.Type.SUB, 3.0, 2.0, 1.0));
        repository.save(new Operation(Operation.Type.MULT, 3.0, 2.0, 6.0));
        repository.save(new Operation(Operation.Type.DIV, 9.0, 3.0, 3.0));

        Operation.Type type = repository.findMostPopularWithinATimePeriod(
                                new Timestamp(0),
                                new Timestamp(System.currentTimeMillis() + 1)).
                                orElse(null);

        Assertions.assertNotNull(type);
        Assertions.assertEquals(Operation.Type.DIV, type);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException")
    public void shouldThrowIllegalArgumentException(){
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                                () -> repository.save(null));
    }
}
