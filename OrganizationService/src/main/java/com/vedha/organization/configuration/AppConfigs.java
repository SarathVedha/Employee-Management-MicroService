package com.vedha.organization.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfigs {

    @Bean
    ModelMapper initilizeModelMapper() {

        return new ModelMapper();
    }

    @Bean
    RestTemplate initilizeRestTemplate() {

        return new RestTemplate();
    }

    @Bean
    WebClient initilizeWebClient() {

        return WebClient.builder().build();
    }
}
