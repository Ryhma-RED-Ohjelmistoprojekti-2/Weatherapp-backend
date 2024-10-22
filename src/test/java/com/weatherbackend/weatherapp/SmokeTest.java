package com.weatherbackend.weatherapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.weatherbackend.weatherapp.web.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmokeTest {

    @Autowired
    WeatherRestController weatherRestController;

    @Value("${DB_USERNAME}")
    private String usernameForDatabase;
    @Value("${DB_PASSWORD}")
    private String passwordForDatabase;
    @Value("${DB_URL}")
    private String urlForDatabase;
    @Value("${AIRPORT_CODE}")
    private String airportCode;
    @Value("${ALLOWED_ORIGINS}")
    private String allowedOrigins;

    @Test
    void contextLoads() {
    }

    @Test
    public void weatherRestControllerNotNull() {
        assertThat(weatherRestController).isNotNull();
    }

    @Test
    public void usernameForDatabaseNotNull() {
        assertThat(usernameForDatabase).isNotNull();
    }

    @Test
    public void passwordForDatabaseNotNull() {
        assertThat(passwordForDatabase).isNotNull();
    }

    @Test
    public void urlForDatabaseNotNull() {
        assertThat(urlForDatabase).isNotNull();
    }

    @Test
    public void airportCodeNotNull() {
        assertThat(airportCode).isNotNull();
    }
}
