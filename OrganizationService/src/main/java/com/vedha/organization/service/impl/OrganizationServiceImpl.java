package com.vedha.organization.service.impl;

import com.vedha.organization.dto.OrganizationDTO;
import com.vedha.organization.entity.OrganizationEntity;
import com.vedha.organization.exception.OrganizationException;
import com.vedha.organization.repository.OrganizationRepository;
import com.vedha.organization.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private ModelMapper modelMapper;

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) {

        OrganizationEntity organizationEntity = modelMapper.map(organizationDTO, OrganizationEntity.class);

        OrganizationEntity save = organizationRepository.save(organizationEntity);

        return modelMapper.map(save, OrganizationDTO.class);
    }

    @Override
    public OrganizationDTO getOrganizationById(Long orgId) {

        OrganizationEntity organizationEntity = organizationRepository.findById(orgId).orElseThrow(() -> new OrganizationException("Organization Not Found : " + orgId));

        return modelMapper.map(organizationEntity, OrganizationDTO.class);
    }

    @Override
    public OrganizationDTO getOrganizationByCode(String orgCode) {

        OrganizationEntity organizationEntity = organizationRepository.findByOrganizationCode(orgCode).orElseThrow(() -> new OrganizationException("Organization Code Not Found : " + orgCode));

        return modelMapper.map(organizationEntity, OrganizationDTO.class);
    }

    @Override
    public List<OrganizationDTO> getAllOrganization() {

        List<OrganizationEntity> all = organizationRepository.findAll();

        return all.stream().map(organizationEntity -> modelMapper.map(organizationEntity, OrganizationDTO.class)).collect(Collectors.toList());
    }
}
