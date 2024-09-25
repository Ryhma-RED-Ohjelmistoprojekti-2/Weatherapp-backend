package com.weatherbackend.weatherapp.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.weatherbackend.weatherapp.domain.Weather;
import com.weatherbackend.weatherapp.domain.WeatherRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WeatherRestController {

    private static final String API_KEY = "my_secret_key"; //TODO: Create real key and put it on .env!

    @Autowired
    WeatherRepository weatherRepository;

    // all weathers
    @GetMapping("/weathers")
    @CrossOrigin(origins = "http://localhost:5173")
    public Iterable<Weather> getWeathers() {

        return weatherRepository.findAll();
    }

    // weather by id
    @GetMapping("/weathers/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public @ResponseBody Optional<Weather> getWeatherById(@PathVariable("id") Long id) {

        return weatherRepository.findById(id);
    } //TODO: Error control?

    @PostMapping("/weathers")
    public ResponseEntity<?> receiveData(@RequestHeader(value = "API-Key", required = false) String apiKey, @Valid @RequestBody Weather weather) {

        if (apiKey == null || !apiKey.equals(API_KEY)) {
            return new ResponseEntity<>("Unauthorized %-P", HttpStatus.UNAUTHORIZED);
        }

        if (weather == null || weather.getDate() == null || weather.getTemperature() == null) {//TODO: Update validation?
            return new ResponseEntity<>("Missing required weather data", HttpStatus.BAD_REQUEST);
        }

        Weather savedWeather = weatherRepository.save(weather);

        return new ResponseEntity<>(Map.of("message", "Weather data saved successfully", "weather", savedWeather), HttpStatus.OK);
    }
}
