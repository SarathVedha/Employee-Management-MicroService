package com.vedha.employee.service.impl;

import com.vedha.employee.dto.DepartmentDTO;
import com.vedha.employee.dto.EmployeeDTO;
import com.vedha.employee.dto.EmployeeResponseDTO;
import com.vedha.employee.dto.OrganizationDTO;
import com.vedha.employee.entity.EmployeeEntity;
import com.vedha.employee.exception.EmployeeException;
import com.vedha.employee.repository.EmployeeRepository;
import com.vedha.employee.service.DepartmentAPI;
import com.vedha.employee.service.EmployeeService;
import com.vedha.employee.service.OrganizationAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private WebClient webClient;

    private RestTemplate restTemplate;

    private ModelMapper modelMapper;

    private DepartmentAPI departmentAPI;

    private OrganizationAPI organizationAPI;

    private EmployeeRepository employeeRepository;

    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "saveEmployeeFallback")
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        // Check Department Code
        try {
            // Direct Service Hitting
            //restTemplate.getForEntity("http://localhost:8082/api/departments/v1/getDepByCode?depCode=" + employeeDTO.getDepCode(), DepartmentDTO.class);

            // Hitting Api-GateWay
            restTemplate.getForEntity("http://localhost:8080/department-service/api/departments/v1/getDepByCode?depCode=" + employeeDTO.getDepCode(), DepartmentDTO.class);

            //webClient.get().uri("http://localhost:8082/api/departments/v1/getDepByCode?depCode=" + employeeDTO.getDepCode()).retrieve().bodyToMono(DepartmentDTO.class).block();
        }catch (Exception e) {

            log.error(e.getMessage());
            throw new EmployeeException("Department Code Not Found : " + employeeDTO.getDepCode());
        }

        // Check Organization
        try {

            webClient.get().uri("http://localhost:8080/organization-service/api/organizations/v1/byCode?orgCode=" + employeeDTO.getOrgCode()).retrieve().bodyToMono(OrganizationDTO.class).block();
        }catch (Exception e) {

            log.error(e.getMessage());
            throw new EmployeeException("Organization Code Not Found : " + employeeDTO.getOrgCode());
        }

        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity save = employeeRepository.save(employeeEntity);

        return modelMapper.map(save, EmployeeDTO.class);
    }

    public EmployeeDTO saveEmployeeFallback(EmployeeDTO employeeDTO, Exception exception) {

        throw new EmployeeException("Services Down, Failed To Save Employee : " + employeeDTO.getEmployeeName());
    }

    @Retry(name = "${spring.application.name}", fallbackMethod = "getByEmployeeIdFallback")
    @Override
    public EmployeeResponseDTO getByEmployeeId(Long employeeId) {

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeException("Employee Not Found : " + employeeId));
        EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);

        DepartmentDTO departmentDTO;
        try {
            //ResponseEntity<DepartmentDTO> departmentDTOResponseEntity = restTemplate.getForEntity("http://localhost:8082/api/departments/v1/getDepByCode?depCode=" + employeeDTO.getDepCode(), DepartmentDTO.class);
            //DepartmentDTO departmentDTO = departmentDTOResponseEntity.getBody();

            //DepartmentDTO departmentDTO = webClient.get().uri("http://localhost:8082/api/departments/v1/getDepByCode?depCode=  " + employeeDTO.getDepCode()).retrieve().bodyToMono(DepartmentDTO.class).block();

            departmentDTO = departmentAPI.getDepartmentCode(employeeDTO.getDepCode());
        } catch (Exception e) {

            log.error(e.getMessage());
            throw new EmployeeException("Department Code Not Found : " + employeeDTO.getDepCode());
        }

        OrganizationDTO organizationDTO;
        try {

            organizationDTO = organizationAPI.getOrgByCode(employeeDTO.getOrgCode());
        } catch (Exception e) {

            log.error(e.getMessage());
            throw new EmployeeException("Organization Code Not Found : " + employeeDTO.getOrgCode());
        }

        return EmployeeResponseDTO.builder().employeeDTO(employeeDTO).departmentDTO(departmentDTO).organizationDTO(organizationDTO).build();
    }

    public EmployeeResponseDTO getByEmployeeIdFallback(Long employeeId, Exception exception) {

        throw new EmployeeException("Services Down, Failed To Fetch Employee : " + employeeId);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        List<EmployeeEntity> all = employeeRepository.findAll();

        return all.stream().map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class)).collect(Collectors.toList());
    }
}
