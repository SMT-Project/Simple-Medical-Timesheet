package com.project.smt.services;

import com.project.smt.configs.ITRuleConfig;
import com.project.smt.entities.Employee;
import com.project.smt.repositories.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest extends ITRuleConfig {

    @Autowired
    private EmployeeServiceImpl service;

    @Autowired
    private EmployeeRepository repository;

    private Employee employee;

    private String id;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        employee = Employee.builder()
                .firstName("First")
                .lastName("Last")
                .patronymicName("Patronymic")
                .email("email@test.com")
                .phone("+0111-222-33-44")
                .build();

        var savedDto = repository.save(service.getEmployeeDTOFromEntity(employee));

        id = savedDto.getId().toString();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
        var result = service.getAll();
        assertEquals(1, result.size());
        assertEquals(id, result.get(0).getId());
    }

    @Test
    void get() {
        var result = service.get(id);
        assertTrue(result.isPresent());
        assertEquals(employee.getFirstName(), result.get().getFirstName());
        assertEquals(employee.getLastName(), result.get().getLastName());
        assertEquals(employee.getPatronymicName(), result.get().getPatronymicName());
        assertEquals(employee.getEmail(), result.get().getEmail());
        assertEquals(employee.getPhone(), result.get().getPhone());
    }

    @Test
    void add() {

    }

    @Test
    void update() {
    }
}