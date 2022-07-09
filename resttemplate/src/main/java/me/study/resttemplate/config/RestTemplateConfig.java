package me.study.resttemplate.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Configuration
public class RestTemplateConfig {

    private final CustomResponseErrorHandler customResponseErrorHandler;

    @Bean
    public RestTemplate basicRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate customRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(customResponseErrorHandler);
        return restTemplate;
    }
}
