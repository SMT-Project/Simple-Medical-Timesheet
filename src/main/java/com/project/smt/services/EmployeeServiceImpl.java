package com.project.smt.services;

import com.project.smt.entities.Employee;
import com.project.smt.exceptions.NotFoundException;
import com.project.smt.repositories.EmployeeRepository;
import com.project.smt.repositories.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employees;

    @Override
    public List<Employee> getAll() {
        return employees.findAll().stream()
                .map(this::getEmployeeFromDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> get(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("Employee id must be provided");
        }
        return employees.findById(UUID.fromString(id)).map(this::getEmployeeFromDTO);
    }

    @Override
    public Employee add(Employee employee) {
        if (Objects.isNull(employee)) {
            throw new IllegalArgumentException("Employee must be provided");
        }
        var dto = getEmployeeDTOFromEntity(employee);
        var savedEmployeeDTO = employees.save(dto);
        return getEmployeeFromDTO(savedEmployeeDTO);
    }


    @Override
    public Employee update(Employee employee) {
        if (Objects.isNull(employee)) {
            throw new IllegalArgumentException("Employee must be provided");
        }
        var dto = getEmployeeDTOFromEntity(employee);
        var oldDto = employees.findById(dto.getId());
        if (oldDto.isEmpty()) {
            throw new NotFoundException("Update failed - Could not find Employee with id: " + employee.getId());
        }
        var updatedDto = employees.save(dto);
        return getEmployeeFromDTO(updatedDto);
    }

    protected Employee getEmployeeFromDTO(EmployeeDTO dto) {
        return Employee.builder()
                .id(dto.getId().toString())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .patronymicName(dto.getPatronymicName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }

    protected EmployeeDTO getEmployeeDTOFromEntity(Employee entity) {
        return EmployeeDTO.builder()
                .id(StringUtils.isBlank(entity.getId())
                        ? null
                        : UUID.fromString(entity.getId())
                )
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .patronymicName(entity.getPatronymicName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
    }
}
