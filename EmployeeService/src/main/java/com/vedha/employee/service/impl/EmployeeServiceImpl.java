package com.vedha.employee.service.impl;

import com.vedha.employee.dto.DepartmentDTO;
import com.vedha.employee.dto.EmployeeDTO;
import com.vedha.employee.dto.EmployeeResponseDTO;
import com.vedha.employee.entity.EmployeeEntity;
import com.vedha.employee.exception.EmployeeException;
import com.vedha.employee.repository.EmployeeRepository;
import com.vedha.employee.service.DepartmentAPI;
import com.vedha.employee.service.EmployeeService;
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

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        try {

            // Direct Service Hitting
            //restTemplate.getForEntity("http://localhost:8082/api/dep/getDepByCode?depCode=" + employeeDTO.getDepCode(), DepartmentDTO.class);

            // Hitting Api-GateWay
            restTemplate.getForEntity("http://localhost:8080/department-service/api/dep/getDepByCode?depCode=" + employeeDTO.getDepCode(), DepartmentDTO.class);

            //webClient.get().uri("http://localhost:8082/api/dep/getDepByCode?depCode=" + employeeDTO.getDepCode()).retrieve().bodyToMono(DepartmentDTO.class).block();

            EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
            EmployeeEntity save = employeeRepository.save(employeeEntity);
            return modelMapper.map(save, EmployeeDTO.class);

        }catch (Exception e) {

            log.error(e.getMessage());
            throw new EmployeeException("Department Code Not Found : " + employeeDTO.getDepCode());
        }
    }

    @Override
    public EmployeeResponseDTO getByEmployeeId(Long employeeId) {

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeException("Employee Not Found : " + employeeId));
        EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);

        try {

            //ResponseEntity<DepartmentDTO> departmentDTOResponseEntity = restTemplate.getForEntity("http://localhost:8082/api/dep/getDepByCode?depCode=" + employeeDTO.getDepCode(), DepartmentDTO.class);
            //DepartmentDTO departmentDTO = departmentDTOResponseEntity.getBody();

            //DepartmentDTO departmentDTO = webClient.get().uri("http://localhost:8082/api/dep/getDepByCode?depCode=  " + employeeDTO.getDepCode()).retrieve().bodyToMono(DepartmentDTO.class).block();

            DepartmentDTO departmentDTO = departmentAPI.getDepartmentCode(employeeDTO.getDepCode());

            return EmployeeResponseDTO.builder().employeeDTO(employeeDTO).departmentDTO(departmentDTO).build();

        }catch (Exception e) {

            log.error(e.getMessage());
            throw new EmployeeException("Department Code Not Found : " + employeeDTO.getDepCode());
        }

    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        List<EmployeeEntity> all = employeeRepository.findAll();

        return all.stream().map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class)).collect(Collectors.toList());
    }
}
