package com.vedha.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeName;

    @Column(nullable = false, unique = true)
    private String employeeEmail;

    @Column(nullable = false)
    private String depCode;

    @Column(nullable = false)
    private String orgCode;
}
