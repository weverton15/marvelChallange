package com.challange.marvel.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.Collections;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfiguration {

    private final SwaggerPropertyConfiguration swaggerPropertyConfiguration;

    @Value("${swagger.host}")
    private String host;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.challange.marvel.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
            swaggerPropertyConfiguration.getTitle(),
            swaggerPropertyConfiguration.getDescription(),
            swaggerPropertyConfiguration.getVersion(),
            swaggerPropertyConfiguration.getTermsOfService(),
            new Contact(
                swaggerPropertyConfiguration.getContactName(),
                swaggerPropertyConfiguration.getContactUrl(),
                swaggerPropertyConfiguration.getContactEmail()),
            "",
            "",
            Collections.emptyList());
    }

    @Component
    @ConfigurationProperties(prefix = "swagger")
    @Getter
    @Setter
    public static class SwaggerPropertyConfiguration {

        private String title;
        private String description;
        private String version;
        private String termsOfService;
        private String contactName;
        private String contactEmail;
        private String contactUrl;
    }
}
