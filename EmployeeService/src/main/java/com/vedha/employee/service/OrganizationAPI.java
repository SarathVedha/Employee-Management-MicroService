package com.vedha.employee.service;

import com.vedha.employee.dto.OrganizationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(url = "http://localhost:8083", value = "ORGANIZATION-SERVICE")
@FeignClient(name = "ORGANIZATION-SERVICE") // If Microservice Registered With Eureka we can use that application name
public interface OrganizationAPI {

    @GetMapping(value = { "/api/organizations/v1/byCode" }, consumes = { MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    OrganizationDTO getOrgByCode(@RequestParam("orgCode") String orgCode);

}
