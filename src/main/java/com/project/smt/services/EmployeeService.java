package com.project.smt.services;

import com.project.smt.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAll();

    Optional<Employee> get(String id);

    Employee add(Employee employee);

    Employee update(Employee employee);

}
