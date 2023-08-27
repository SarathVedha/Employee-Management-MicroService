package com.vedha.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private Long id;

    @NotEmpty(message = "Employee Name Should Not Be Empty")
    @Size(min = 5, max = 30, message = "Employee Name Should Be 5-30 Chars")
    private String employeeName;

    @NotEmpty(message = "Employee Email Should Not Be Empty")
    @Email(message = "Invalid Employee Email")
    private String employeeEmail;

    @NotEmpty(message = "Department Code Should Not Be Empty")
    @Size(min = 5, max = 5, message = "Department Code Should Be 5 Chars")
    private String depCode;
}
