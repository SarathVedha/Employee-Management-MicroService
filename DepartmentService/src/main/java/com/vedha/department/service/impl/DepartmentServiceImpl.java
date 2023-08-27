package com.vedha.department.service.impl;

import com.vedha.department.dto.DepartmentDTO;
import com.vedha.department.entity.DepartmentEntity;
import com.vedha.department.exception.DepartmentException;
import com.vedha.department.repository.DepartmentRepository;
import com.vedha.department.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private ModelMapper modelMapper;

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {

        boolean exists = departmentRepository.existsByDepartmentCode(departmentDTO.getDepartmentCode());

        if (exists) {

            throw new DepartmentException("Department Code Already Exists: " + departmentDTO.getDepartmentCode());
        }else {

            DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
            DepartmentEntity save = departmentRepository.save(departmentEntity);
            return modelMapper.map(save, DepartmentDTO.class);
        }
    }

    @Override
    public DepartmentDTO getDepByDepCode(String departmentCode) {

        DepartmentEntity departmentEntity = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(() -> new DepartmentException("Department Code Not Found : " + departmentCode));

        return modelMapper.map(departmentEntity, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {

        List<DepartmentEntity> all = departmentRepository.findAll();

        return all.stream().map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class)).collect(Collectors.toList());
    }
}
