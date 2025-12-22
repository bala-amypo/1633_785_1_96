package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public java.time.Clock systemClock() {
        return java.time.Clock.systemDefaultZone();
    }
}
