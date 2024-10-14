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
    http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorize -> authorize
      .requestMatchers(antMatcher("/api/**")).permitAll()
    ); //TODO: Restore old code. ^^ This disables security
    // http.authorizeHttpRequests(authorize -> authorize
    //   .requestMatchers(antMatcher("/api/**")).permitAll()
    // );
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
          "http://localhost:5173" // TODO: REMOVE FROM RELEASE!
        )
        .allowedHeaders("Content-Type")
        .allowedMethods("GET", "POST") 
        .allowedHeaders("application/json")
        .allowCredentials(false);
      }
    };
  }
}

/*
 * This determines who can interact 
 * with backend via network connection and 
 * which requests are allowed
 */