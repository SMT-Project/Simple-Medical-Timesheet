package com.project.smt.controllers;

import com.project.smt.entities.Employee;
import com.project.smt.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping({"api/v1/employees/"})
public class EmployeeController {
    private final EmployeeService service;

    @CrossOrigin
    @GetMapping
    public Collection<Employee> getAll() {
        log.debug("EmployeeController.getAll() was called");
        return service.getAll();
    }
}
