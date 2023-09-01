package com.vedha.organization.controller;

import com.vedha.organization.dto.OrganizationDTO;
import com.vedha.organization.service.OrganizationService;
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
@RequestMapping("/api/organizations")
@AllArgsConstructor
@Tag(name = "Organization REST APIs", description = "Organization REST CURD APIs")
public class OrganizationController {

    private OrganizationService organizationService;

    @Operation(summary = "Save Organization", description = "Save organization In DataBase")
    @ApiResponse(responseCode = "201", description = "Http Status 201 Created")
    @PostMapping(value = { "v1/save" }, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<OrganizationDTO> saveOrg(@Valid @RequestBody OrganizationDTO organizationDTO) {

        OrganizationDTO savedOrganization = organizationService.saveOrganization(organizationDTO);

        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Organization By Id", description = "Get Organization By Id In DataBase")
    @ApiResponse(responseCode = "200", description = "Http Status 200 Ok")
    @GetMapping(value = { "v1/byId" }, consumes = { MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<OrganizationDTO> getOrgById(@RequestParam("orgId") Long orgId) {

        OrganizationDTO organizationById = organizationService.getOrganizationById(orgId);

        return ResponseEntity.ok(organizationById);
    }

    @Operation(summary = "Get Organization By Code", description = "Get Organization By Code In DataBase")
    @ApiResponse(responseCode = "200", description = "Http Status 200 Ok")
    @GetMapping(value = { "/v1/byCode" }, consumes = { MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<OrganizationDTO> getOrgByCode(@RequestParam("orgCode") String orgCode) {

        OrganizationDTO organizationByCode = organizationService.getOrganizationByCode(orgCode);

        return ResponseEntity.ok(organizationByCode);
    }

    @Operation(summary = "Get All Organization", description = "Get All Organization In DataBase")
    @ApiResponse(responseCode = "200", description = "Http Status 200 Ok")
    @GetMapping(value = { "v1/all" }, consumes = { MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<OrganizationDTO>> getAllOrg() {

        List<OrganizationDTO> allOrganization = organizationService.getAllOrganization();

        return ResponseEntity.ok(allOrganization);
    }

}
