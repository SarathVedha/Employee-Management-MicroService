package com.vedha.organization.repository;

import com.vedha.organization.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

    Optional<OrganizationEntity> findByOrganizationCode(String orgCode);
}
