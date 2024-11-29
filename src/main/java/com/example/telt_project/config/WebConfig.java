package com.example.telt_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // CORS configuration
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // Explicitly allow your frontend origin
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("PATCH");
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
