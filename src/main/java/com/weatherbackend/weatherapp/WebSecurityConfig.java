package com.weatherbackend.weatherapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Value("${ALLOWED_ORIGIN_1:}")
  private String allowedOrigin1;

  @Value("${ALLOWED_ORIGIN_2:}")
  private String allowedOrigin2;

  @Value("${ALLOWED_ORIGIN_3:}")
  private String allowedOrigin3;

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
            allowedOrigin1,
            allowedOrigin2,
            allowedOrigin3,
            "http://localhost:5173"
          )
          .allowedHeaders("Content-Type")
          .allowedMethods("GET", "POST", "DELETE") 
          .allowedHeaders("application/json")
          .allowCredentials(false)
        ;
      }
    };
  }
}

/*
 * This determines who can interact 
 * with backend via network connection and 
 * which requests are allowed
 */