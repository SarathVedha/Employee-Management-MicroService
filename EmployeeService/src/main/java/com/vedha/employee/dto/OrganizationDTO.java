package com.vedha.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationDTO {

    private Long id;

    private String organizationName;

    private String organizationDescription;

    private String organizationCode;

}
