package com.vedha.employee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/api/test")
@Tag(name = "Config Server", description = "Testing Config Server")
public class ConfigServerTest {

    @Value("${test.config.server}")
    private String message;

    @Operation(summary = "Properties Message", description = "Message From Employee Properties")
    @ApiResponse(responseCode = "200", description = "Http Status 200 Ok")
    @GetMapping("/message")
    public ResponseEntity<String> testConfigServer() {

        return ResponseEntity.ok(message);
    }
}
