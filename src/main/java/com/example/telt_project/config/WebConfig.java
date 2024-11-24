// package com.example.telt_project.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {

//     @Bean
//     public CorsFilter corsFilter() {
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         CorsConfiguration config = new CorsConfiguration();
        
//         // CORS configuration
//         config.setAllowCredentials(true);
//         config.addAllowedOriginPattern("*"); // Allow all origins
//         config.addAllowedHeader("*"); // Allow all headers
//         config.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, etc.)
        
//         source.registerCorsConfiguration("/**", config);
//         return new CorsFilter(source);
//     }

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         // Add specific mappings if needed for development
//         registry.addMapping("/api/**").allowedOrigins("http://localhost:3000");
//     }
// }


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
        config.addAllowedOrigin("http://localhost:3000"); // Explicitly allow your frontend origin
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, etc.)
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
