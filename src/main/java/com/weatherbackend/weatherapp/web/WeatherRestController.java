package com.weatherbackend.weatherapp.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.weatherbackend.weatherapp.domain.Weather;
import com.weatherbackend.weatherapp.domain.WeatherRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class WeatherRestController {

    // weather repository
    @Autowired
    WeatherRepository weatherRepository;

    // all weathers
    @GetMapping("/api/weathers")
    @CrossOrigin(origins = "http://localhost:5173")
    public Iterable<Weather> getWeathers() {

        return weatherRepository.findAll();
    }

    // weather by id
    @GetMapping("/api/weathers/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public @ResponseBody Optional<Weather> getWeatherById(@PathVariable("id") Long id) {

        return weatherRepository.findById(id);
    }
}
