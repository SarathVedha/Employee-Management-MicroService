package com.vedha.employee.service;

import com.vedha.employee.dto.EmployeeDTO;
import com.vedha.employee.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    EmployeeResponseDTO getByEmployeeId(Long employeeId);

    List<EmployeeDTO> getAllEmployees();
}
