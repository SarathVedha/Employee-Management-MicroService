package com.vedha.organization.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessageDTO {

    private String errorCode;

    private String errorMessage;

    private LocalDateTime timeStamp;

    private String uriPath;
}
