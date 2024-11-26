package com.weatherbackend.weatherapp;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherbackend.weatherapp.domain.Weather;
import com.weatherbackend.weatherapp.domain.WeatherRepository;

@SpringBootApplication
public class WeatherappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherappApplication.class, args);
    }
}

@Service
class WeatherUpdateService {

    @Autowired
    private WeatherRepository weatherRepository;

    @PostConstruct
    public void init() {
        scheduleAutomaticUpdate();
    }

    private void scheduleAutomaticUpdate() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {
            try {
                System.out.println("Executing scheduled updateWeather...");
                updateWeather();
            } catch (Exception e) {
                System.err.println("Error during scheduled updateWeather: " + e.getMessage());
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    private void updateWeather() {
        try {
            String userDir = System.getProperty("user.dir");
            String weatherTxtPath = WeatherTxtLocator.getWeatherTxtPath(userDir);

            Weather newWeather = getWeatherObject(weatherTxtPath);
            System.out.println(newWeather);
            if (isWeatherComplete(newWeather)) {
                try {
                    long recordCount = weatherRepository.count();
                    if (recordCount >= 3) { //Maximum amount of weather records kept.
                        Weather oldestWeather = weatherRepository.findFirstByOrderByDateAscTimeAsc();
                        if (oldestWeather != null) {
                            weatherRepository.delete(oldestWeather);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("No existing records.");
                }
                weatherRepository.save(newWeather);
            } else {
                System.err.println("newWeather was invalid!");
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    private Weather getWeatherObject(String path) {
        Weather weatherData = new Weather();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            weatherData = objectMapper.readValue(new File(path), Weather.class);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return weatherData;
    }

    private boolean isWeatherComplete(Weather w) {
        return w.getRainfallOneHour() != null && w.getMaxWindSpeed() != null &&
                w.getTemperature() != null && w.getHumidity() != null &&
                w.getRainfallTwentyFourHour() != null
                && w.getBarometricPressure() != null && w.getWindDirection() != null &&
                w.getAvgWindSpeed() != null && w.getDate() != null && w.getTime() != null;
    }
}
