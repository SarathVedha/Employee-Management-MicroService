package com.vedha.employee.service;

import com.vedha.employee.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(url = "http://localhost:8082", value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE") // If Microservice Registered With Eureka we can use that application name
public interface DepartmentAPI {

    @GetMapping(value = { "/api/departments/v1/getDepByCode" },
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    DepartmentDTO getDepartmentCode(@RequestParam("depCode") String departmentCode);

}
