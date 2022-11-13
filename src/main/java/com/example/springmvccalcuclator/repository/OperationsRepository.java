package com.example.springmvccalcuclator.repository;

import com.example.springmvccalcuclator.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Optional;

public interface OperationsRepository
        extends JpaRepository<Operation, Long> {

    @Query(value = """
            SELECT type
            FROM operations
            WHERE created_at BETWEEN ?1 AND ?2
            GROUP BY type
            ORDER BY COUNT(type) DESC
            LIMIT 1
            """,
    nativeQuery = true)
    Optional<Operation.Type> findMostPopularWithinATimePeriod(Timestamp leftBorder, Timestamp rightBorder);
}

