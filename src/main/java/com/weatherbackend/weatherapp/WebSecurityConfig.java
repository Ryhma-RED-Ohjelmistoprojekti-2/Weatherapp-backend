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
      .requestMatchers(antMatcher("/api/**")).permitAll()
    );
    return http.build();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
        .allowedOrigins(
          "https://softala.haaga-helia.fi", //TODO: Fix the address if needed
          "http://localhost:5173" //TODO: REMOVE FROM RELEASE!
        )
        .allowedHeaders("Content-Type")
        .allowedMethods("GET", "POST") //TODO: Allow more methods?
        .allowedHeaders("application/json")
        .allowCredentials(false);
        //.maxAge(3600); //TODO: Change this so that there is validation for weather data?
      }
    };
  }
}
