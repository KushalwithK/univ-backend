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
                        .allowedOrigins("http://192.168.89.169:5173", "http://192.168.91.106:5173")
                        .allowedHeaders("*")
//                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                        .allowCredentials(false).maxAge(3600);
            }
}
