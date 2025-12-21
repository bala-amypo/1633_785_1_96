package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Leave Overlap & Team Capacity Analyzer API")
                        .version("1.0"))
                // ✅ SERVER URL (same as screenshot)
                .servers(List.of(
                        new Server().url("https://9240.408procr.amypo.ai/")
                ))
                // ✅ JWT Bearer
                .addSecurityItem(
                        new SecurityRequirement().addList(securitySchemeName)
                )
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(
                                securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
