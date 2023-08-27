package com.vedha.department.repository;

import com.vedha.department.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    Optional<DepartmentEntity> findByDepartmentCode(String departmentCode);

    boolean existsByDepartmentCode(String departmentCode);
}
