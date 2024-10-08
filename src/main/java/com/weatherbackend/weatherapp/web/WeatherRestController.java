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

    @Autowired
    WeatherRepository weatherRepository;

    @GetMapping("/weathers")
    public Iterable<Weather> getWeathers() {

        return weatherRepository.findAll();
    }

    @GetMapping("/weathers/{id}")
    public @ResponseBody Optional<Weather> getWeatherById(@PathVariable("id") Long id) {

        return weatherRepository.findById(id);
    } //TODO: Error control/validation?

    @PostMapping("/weathers")
    public ResponseEntity<?> receiveData(@Valid @RequestBody Weather weather) {

        //TODO: Complete the if statement or make better validation
        if (weather == null || weather.getDate() == null || weather.getTemperature() == null) {
            return new ResponseEntity<>("Missing required weather data", HttpStatus.BAD_REQUEST);
        }

        Weather savedWeather = weatherRepository.save(weather);

        return new ResponseEntity<>(Map.of("message", "Weather data saved successfully", "weather", savedWeather), HttpStatus.OK);
    }
}
