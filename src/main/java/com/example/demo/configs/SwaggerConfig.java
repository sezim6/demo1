package com.example.demo.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class SwaggerConfig {
    private SecurityScheme key() {
        return new SecurityScheme()
                .name("Authorization")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .description("jwt put token")
                .scheme("Bearer");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer", key()))
                .info(new Info().title("Hello").description("World"))
                .security(List.of(new SecurityRequirement().addList("Bearer")));
    }
}
