package com.project.smt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.smt.repositories.dto.EmployeeDTO;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO, UUID> {
}
