package com.example.testVulkan_Backend_SPRINGBOOT341;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/course/**", "/student/**", "/stadistics/**").authenticated()  // Estas rutas requieren autenticación
                        .anyRequest().permitAll()  // Permite todas las demás rutas sin autenticación
                )
                .httpBasic(Customizer.withDefaults())  // Autenticación básica
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF para API
                .formLogin().disable();  // Deshabilitar formulario de login si es que lo usas

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("vicente")
                .password(passwordEncoder.encode("abc123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:8081");  // Permitir tu frontend
        corsConfig.addAllowedMethod("*");  // Permitir todos los métodos HTTP
        corsConfig.addAllowedHeader("*");  // Permitir todos los encabezados
        corsConfig.setAllowCredentials(true);  // Permitir el envío de cookies y cabeceras de autenticación

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // Configuración global para todos los endpoints

        return new CorsFilter(source);
    }

}
