package com.example.pokedex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                // Permetti l'accesso libero a queste risorse
                .requestMatchers("/", "/index.html", "/styles.css", "/images/**", "/api/pokemons/**").permitAll()
                // Permetti l'accesso libero alle pagine di autenticazione
                .requestMatchers("/login.html", "/register.html", "/api/auth/**").permitAll()
                // Tutte le altre richieste richiedono autenticazione
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login.html")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/api/auth/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(HttpServletResponse.SC_OK);
                })
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());  // Disabilita CSRF per semplicità

        return http.build();
    }
    
}
