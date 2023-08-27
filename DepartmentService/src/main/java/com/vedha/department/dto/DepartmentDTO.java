package com.vedha.department.dto;

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
public class DepartmentDTO {

    private Long id;

    @NotEmpty(message = "Department Name Should Not Be Empty")
    @Size(min = 5, max = 30, message = "Department Name Should Be 5-30 Chars")
    private String departmentName;

    @NotEmpty(message = "Department Description Should Not Be Empty")
    @Size(min = 5, max = 40, message = "Department Description Should Be 5-40 Chars")
    private String departmentDesc;

    @NotEmpty(message = "Department Code Should Not Be Empty")
    @Size(min = 5, max = 5, message = "Department Code Should Be 5 Chars")
    private String departmentCode;
}
