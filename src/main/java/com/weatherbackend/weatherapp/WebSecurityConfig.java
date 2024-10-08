package com.weatherbackend.weatherapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(authorize -> authorize
        .requestMatchers(antMatcher("/api/**")).permitAll());
    return http.build();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
          .allowedOrigins(
          "https://softala.haaga-helia.fi", // TODO: Fix the address if needed
          "http://localhost:5173"
        )
          .allowedHeaders("Content-Type")
          .allowedMethods("GET", "POST") // TODO: Allow more methods?
          .allowedHeaders("application/json")
          .allowCredentials(false);
        // .maxAge(3600); //TODO: Change this so that there is validation for weather data?
      }
    };
  }
}

/*
 * According to Copilot:
 * 
 * WebMvcConfigurer (interface in Spring Framework) customises default configs
 * of Spring MVC.
 * 
 * Options addCorsMappings, addMapping and allowedOrigins configures
 * how the backend interacts with frontend and softala.haaga-helia.fi .
 * 
 * Particularly in therms of handling Cross-Origin Resource Sharing (CORS)
 * [a security feature implemented by browsers to prevent web pages from
 * requesting to another domain] and API endpoint mappings.
 * 
 * In other words, these are setting up the backend to allow
 * the frontend (URL) and softala.haaga-helia.fi to make requests
 * to the backend API endpoints. Very crucial when they are all
 * hosted on different domains.
 * 
 * api/** means that configs will be applied to all API endpoints.
 */