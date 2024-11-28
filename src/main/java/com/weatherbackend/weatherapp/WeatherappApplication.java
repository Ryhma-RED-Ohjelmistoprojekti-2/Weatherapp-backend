package com.weatherbackend.weatherapp;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

//import com.fasterxml.jackson.databind.ObjectMapper;
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
            String directory = System.getProperty("user.dir"); //Change this if app is executed from different directory!
            String weatherTxtPath = WeatherTxtLocator.getWeatherTxtPath(directory);

            Weather newWeather = parseWeatherObject(weatherTxtPath);
            if (isWeatherComplete(newWeather)) {
                try {
                    long recordCount = weatherRepository.count();
                    if (recordCount >= 10) {
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
                System.err.println("newWeather was invalid: " + newWeather);
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    //Here is method that could be used for normal json in the future!
    // private Weather getWeatherObject(String path) {
    //     Weather newWeather = new Weather();
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     try {
    //         newWeather = objectMapper.readValue(new File(path), Weather.class);
    //     } catch (Exception e) {
    //         System.err.println("ERROR: " + e.getMessage());
    //     }
    //     return newWeather;
    // }

    private Weather parseWeatherObject(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            Weather newWeather = new Weather();
            String regex = "(\\w+):\\s*([0-9.]+)";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
    
                java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(regex).matcher(line);
                while (matcher.find()) {
                    String key = matcher.group(1).trim().toLowerCase();
                    String value = matcher.group(2).trim();
    
                    switch (key) {
                        case "winddirection":
                            newWeather.setWindDirection(Integer.parseInt(value));
                            break;
                        case "rainfallonehourmm":
                            newWeather.setRainfallOneHour(Float.parseFloat(value));
                            break;
                        case "maxwindspeedfiveminutesmeterpersecond":
                            newWeather.setMaxWindSpeed(Float.parseFloat(value));
                            break;
                        case "temperaturecelsius":
                            newWeather.setTemperature(Float.parseFloat(value));
                            break;
                        case "humiditypercentage":
                            newWeather.setHumidity(Integer.parseInt(value));
                            break;
                        case "barometricpressurehpa":
                            newWeather.setBarometricPressure(Float.parseFloat(value));
                            break;
                        case "averagewindspeedoneminutemeterpersecond":
                            newWeather.setAvgWindSpeed(Float.parseFloat(value));
                            break;
                        case "rainfall24hourmm":
                            newWeather.setRainfallTwentyFourHour(Float.parseFloat(value));
                            break;
                        default:
                            break;
                    }
                }
                if (isWeatherComplete(newWeather)) break;
            }
            return newWeather;
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    private boolean isWeatherComplete(Weather w) {
        return w.getRainfallOneHour() != null && w.getMaxWindSpeed() != null &&
                w.getTemperature() != null && w.getHumidity() != null &&
                w.getRainfallTwentyFourHour() != null
                && w.getBarometricPressure() != null && w.getWindDirection() != null &&
                w.getAvgWindSpeed() != null;
    }
}
