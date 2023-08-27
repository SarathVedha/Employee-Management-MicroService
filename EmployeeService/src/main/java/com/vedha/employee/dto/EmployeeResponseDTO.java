package com.vedha.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponseDTO {

    private EmployeeDTO employeeDTO;

    private DepartmentDTO departmentDTO;
}
