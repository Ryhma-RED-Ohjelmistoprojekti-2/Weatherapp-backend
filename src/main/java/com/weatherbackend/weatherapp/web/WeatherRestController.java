package com.weatherbackend.weatherapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import com.weatherbackend.weatherapp.domain.Weather;
import com.weatherbackend.weatherapp.domain.WeatherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("/api")
public class WeatherRestController {

    @Autowired
    WeatherRepository weatherRepository;

    @GetMapping("/weathers")
    public ResponseEntity<List<Weather>> getWeathers(
        @RequestParam(defaultValue = "0") int page, // This is set to 0 so that the first group of records is returned 
        @RequestParam(defaultValue = "20") int size  // This defines how many records are returned
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Weather> latestWeathers = weatherRepository.findAllByOrderByDateDescTimeDesc(pageRequest);

        List<Weather> weatherList = latestWeathers.getContent();

        return new ResponseEntity<>(weatherList, HttpStatus.OK);
        // return weatherRepository.findAll(); // 
    }

    @GetMapping("/weathers/{id}")
    public ResponseEntity<?> getWeatherById(@PathVariable("id") Long id) {
        Optional<Weather> weather = weatherRepository.findById(id);
        if (weather.isPresent()) {
            return new ResponseEntity<>(weather.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Weather record not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/weathers")
    public ResponseEntity<?> receiveData(@Valid @RequestBody Weather weather) {

        if (weather == null || 
            weather.getTemperature() == null || 
            weather.getHumidity() == null || 
            weather.getDate() == null || 
            weather.getTime() == null ||
            weather.getBarometricPressure() == null || 
            weather.getWindDirection() == null || 
            weather.getAvgWindSpeed() == null || 
            weather.getMaxWindSpeed() == null || 
            weather.getRainfallOneHour() == null || 
            weather.getRainfallTwentyFourHour() == null
        ) {
            return new ResponseEntity<>("Missing required weather data", HttpStatus.BAD_REQUEST);
        }

        Weather savedWeather = weatherRepository.save(weather);
        return new ResponseEntity<>(Map.of("message", "Weather data saved successfully", "weather", savedWeather), HttpStatus.OK);
    }
}