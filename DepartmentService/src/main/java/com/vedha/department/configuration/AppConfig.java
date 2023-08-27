package com.vedha.department.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    ModelMapper initializeModelMapper() {

        return new ModelMapper();
    }

    @Bean
    RestTemplate initializeRestTemplate() {

        return new RestTemplate();
    }

    @Bean
    WebClient inilializeWebClient() {

        return WebClient.builder().build();
    }
}
