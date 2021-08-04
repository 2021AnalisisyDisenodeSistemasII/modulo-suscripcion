package com.povsal.starbank.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration  {

    @Bean
    public WebMvcConfigurer setCors() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("*")
                        .allowedOrigins("http://127.0.0.1:5500");
            }
        };
    }
}
