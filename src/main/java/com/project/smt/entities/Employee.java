package com.project.smt.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String patronymicName;
    private String email;
    private String phone;
}
