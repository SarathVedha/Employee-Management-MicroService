package com.vedha.organization.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "Organization Name Should Not Be Empty")
    @Size(min = 5, max = 25, message = "Organization Name Should Be 5-25 Chars")
    private String organizationName;

    @NotEmpty(message = "Organization Description Should Not Be Empty")
    @Size(min = 5, max = 30, message = "Organization Description Should Be 5-30 Chars")
    private String organizationDescription;

    @NotEmpty(message = "Organization Code Should Not Be Empty")
    @Size(min = 5, max = 5, message = "Organization Code Should Be 5 Chars")
    private String organizationCode;
}
