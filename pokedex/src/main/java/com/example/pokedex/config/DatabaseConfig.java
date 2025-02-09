package com.example.pokedex.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.pokedex.repository")  // modificato da .auth
@EntityScan(basePackages = "com.example.pokedex.model")                 // modificato da .auth
class DatabaseConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
