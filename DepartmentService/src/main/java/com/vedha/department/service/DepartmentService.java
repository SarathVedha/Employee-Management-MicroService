package com.vedha.department.service;

import com.vedha.department.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepByDepCode(String departmentCode);

    List<DepartmentDTO> getAllDepartments();
}
