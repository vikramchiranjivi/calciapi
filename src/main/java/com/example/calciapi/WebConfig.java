package com.example.calciapi; // Or use your actual base package

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // This annotation tells Spring to treat this as a configuration class
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow cross-origin requests from your frontend
        registry.addMapping("/**")  // Allows CORS for all endpoints
                .allowedOrigins("http://localhost:8080"); // Allow requests from this frontend URL
    }
}
