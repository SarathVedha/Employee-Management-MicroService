package com.vedha.employee.controller;

import com.vedha.employee.dto.EmployeeDTO;
import com.vedha.employee.dto.EmployeeResponseDTO;
import com.vedha.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emp")
@AllArgsConstructor
@Tag(name = "Employee REST APIs", description = "Employee CURD REST APIs")
public class EmployeeController {

    private EmployeeService employeeService;

    @Operation(summary = "Create Employee", description = "Create Employee In DataBase")
    @ApiResponse(responseCode = "201", description = "Http Status 201 Created")
    @PostMapping(value = { "/save" },
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO saved = employeeService.saveEmployee(employeeDTO);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Employee By Id", description = "Get Employee By Id In DataBase")
    @ApiResponse(responseCode = "200", description = "Http Status 200 Ok")
    @GetMapping(value = { "/getById" },
            consumes = { MediaType.ALL_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@RequestParam("empId") Long employeeId) {

        EmployeeResponseDTO byEmployeeId = employeeService.getByEmployeeId(employeeId);

        return ResponseEntity.ok(byEmployeeId);
    }

    @Operation(summary = "Get All Employees", description = "Get All Employee In DataBase")
    @ApiResponse(responseCode = "200", description = "Http Status 200 Ok")
    @GetMapping(value = { "/allEmp" },
            consumes = { MediaType.ALL_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
