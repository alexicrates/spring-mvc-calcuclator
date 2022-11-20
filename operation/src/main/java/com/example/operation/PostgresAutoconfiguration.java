package com.example.operation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(DataSource.class)
@EnableJpaRepositories
@ComponentScan
@EntityScan
public class PostgresAutoconfiguration {
    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:2345/OperationsDB");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");

        return dataSource;
    }
}
