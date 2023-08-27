package com.vedha.department.controller;

import com.vedha.department.dto.DepartmentDTO;
import com.vedha.department.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/api/dep")
@AllArgsConstructor
@Tag(name = "Department REST APIs", description = "Department CURD REST APIs")
public class DepartmentController {

    private DepartmentService departmentService;

    @Operation(summary = "Create Department", description = "Creates Department In DataBase")
    @ApiResponse(responseCode = "201", description = "Http Status 201 Created")
    @PostMapping(value = { "/saveDep" },
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<DepartmentDTO> saveDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {

        DepartmentDTO saveDepartment = departmentService.saveDepartment(departmentDTO);

        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Department By Code", description = "Get Department By Code In DataBase")
    @ApiResponse(responseCode = "200", description = "Http Status 200 Ok")
    @GetMapping(value = { "/getDepByCode" },
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<DepartmentDTO> getByDepartmentCode(@RequestParam("depCode") String departmentCode) {

        DepartmentDTO depByDepCode = departmentService.getDepByDepCode(departmentCode);

        return ResponseEntity.ok(depByDepCode);
    }

    @Operation(summary = "Get All Department", description = "Get All Department In DataBase")
    @ApiResponse(responseCode = "200", description = "Http Status 200 Ok")
    @GetMapping(value = { "/getAllDep" },
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment() {

        List<DepartmentDTO> allDepartments = departmentService.getAllDepartments();

        return ResponseEntity.ok(allDepartments);
    }

}
