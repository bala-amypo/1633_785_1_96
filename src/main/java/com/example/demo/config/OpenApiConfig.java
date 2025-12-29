package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Server server = new Server();
        server.setUrl("https://9520.pro604cr.amypo.ai/");
        server.setDescription("Production Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Leave Overlap Team Capacity Analyzer API")
                        .version("1.0")
                        .description("API for managing employee leaves and team capacity analysis"))
                .addServersItem(server);
    }
}
