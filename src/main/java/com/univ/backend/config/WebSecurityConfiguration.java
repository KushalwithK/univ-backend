package com.univ.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(
                        "http://192.168.89.169:5173",
                        "http://172.20.10.2:5173",
                        "http://172.20.10.2:5174",
                        "http://153.92.4.150",
                        "https://univsportatech.in",
                        "https://www.univsportatech.in",
                        "https://admin.univsportatech.in")
                .allowedHeaders("*")
                // .exposedHeaders("Access-Control-Allow-Origin",
                // "Access-Control-Allow-Credentials")
                .allowCredentials(false).maxAge(3600);
    }
}
