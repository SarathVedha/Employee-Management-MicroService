package com.vedha.organization.service;

import com.vedha.organization.dto.OrganizationDTO;

import java.util.List;

public interface OrganizationService {

    OrganizationDTO saveOrganization(OrganizationDTO organizationDTO);

    OrganizationDTO getOrganizationById(Long orgId);

    OrganizationDTO getOrganizationByCode(String orgCode);

    List<OrganizationDTO> getAllOrganization();
}
