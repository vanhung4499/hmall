package com.hnv99.hmall.common.apidoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * OpenAPI Configuration Class
 * <p>
 * Based on OpenAPI 3.0 specification + SpringDoc implementation + knife4j enhancement
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ApiDocInfoProperties.class)
public class OpenApiConfig {

    /**
     * API Documentation Information Properties
     */
    private final ApiDocInfoProperties apiDocInfoProperties;


    /**
     * OpenAPI Configuration (metadata, security protocol)
     */
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(HttpHeaders.AUTHORIZATION,
                                new SecurityScheme()
                                        // OAuth2 authorization mode
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .name(HttpHeaders.AUTHORIZATION)
                                        // Security mode uses Bearer token (i.e., JWT)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                        )
                )
                // Add Authorization parameter globally to the interface
                .addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
                // Interface documentation information (not important)
                .info(new Info()
                        .title(apiDocInfoProperties.getTitle())
                        .version(apiDocInfoProperties.getVersion())
                        .description(apiDocInfoProperties.getDescription())
                        .contact(new Contact()
                                .name(apiDocInfoProperties.getContact().getName())
                                .url(apiDocInfoProperties.getContact().getUrl())
                                .email(apiDocInfoProperties.getContact().getEmail())
                        )
                        .license(new License().name(apiDocInfoProperties.getLicense().getName())
                                .url(apiDocInfoProperties.getLicense().getUrl())
                        ));
    }

}
