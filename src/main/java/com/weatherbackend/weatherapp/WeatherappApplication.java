package com.weatherbackend.weatherapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.weatherbackend.weatherapp.domain.Weather;
import com.weatherbackend.weatherapp.domain.WeatherRepository;

@SpringBootApplication
public class WeatherappApplication {

	@Autowired
	WeatherRepository weatherRepository;

	public static void main(String[] args) {
		SpringApplication.run(WeatherappApplication.class, args);
	}

	@Bean
	CommandLineRunner test() {
		return (args) -> {
		readFiles("src/main/resources/weather_data_autumn_2024/weather_20240829_135303.txt");
		readFiles("src/main/resources/weather_data_autumn_2024/weather_20240829_141538.txt");
		readFiles("src/main/resources/weather_data_autumn_2024/weather_20240829_140454.txt");
		};
	}



	private static boolean isWeatherComplete(Weather we) {
		return we.getRainfallOneHour() != null && we.getMaxWindSpeed() != null &&
		we.getTemperature() != null && we.getHumidity() != null && we.getRainfallTwentyFourHour() != null && we.getBarometricPressure() != null && we.getWindDirection() != null && we.getAvgWindSpeed() != null;
	}

	//HUOM! Tässä aiheutuu resurssivuoto, koska Scanner scanneria ei koskaan suljeta!
	
	// private Weather readFiles(String path) {
	// 	List<Weather> data = new ArrayList<>();
	// 	try {
	// 		Weather we = new Weather();
	// 		File weatherdata = new File(path);
	// 		Scanner scanner = new Scanner(weatherdata);
	// 		while (scanner.hasNextLine()) {
	// 			String line = scanner.nextLine();
	// 			String numericValue = line.replaceAll("[^0-9.]", "").trim();
	// 			String name = weatherdata.getName();
    //             we.setDate(name.substring(8, 16));
    //             we.setTime(name.substring(17, name.length() - 4));
    //             if (line.contains("Wind Direction")) {
    //                 we.setWindDirection(Integer.parseInt(numericValue));
    //             } else if (line.contains("Rain Fall (One Hour)")) {
    //                 we.setRainfallOneHour(Float.parseFloat(numericValue));
    //             } else if (line.contains("Max Wind Speed (Five Minutes):")) {
    //                 we.setMaxWindSpeed(Float.parseFloat(numericValue));
    //             } else if (line.contains("Temperature:")) {
    //                 we.setTemperature(Float.parseFloat(numericValue));
    //             } else if (line.contains("Humidity:")) {
    //                 we.setHumidity(Integer.parseInt(numericValue));
    //             } else if (line.contains("Barometric Pressure:")) {
    //                 we.setBarometricPressure(Float.parseFloat(numericValue));
    //             } else if (line.contains("Average Wind Speed (One Minute)")) {
    //                 we.setAvgWindSpeed(Float.parseFloat(numericValue));
    //             } else if (line.contains("Rain Fall (24 Hour):")) {
    //                 we.setRainfallTwentyFourHour(Float.parseFloat(numericValue.substring(2)));
    //             }
	// 			if (isWeatherComplete(we)) {
	// 				weatherRepository.save(we);
    //                 data.add(we);
    //                 break;
    //             }
	// 		}
	// 		System.out.println("\n\n"+we.toString() + "\n\n");
	// 		return we;
	// 	} catch (Exception error) {
	// 		System.out.println("\n\n " + error + " \n\n\n");
	// 	}
	// 	return null;
	// };

	private Weather readFiles(String path) {
		List<Weather> data = new ArrayList<>();
		try {
			Weather we = new Weather();
			File weatherdata = new File(path);
	
			// Try-with-resources sulkee scannerin automaattisesti
			try (Scanner scanner = new Scanner(weatherdata)) {
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String numericValue = line.replaceAll("[^0-9.]", "").trim();
					String name = weatherdata.getName();
					
					we.setDate(name.substring(8, 16));
					we.setTime(name.substring(17, name.length() - 4));
	
					if (line.contains("Wind Direction")) {
						we.setWindDirection(Integer.parseInt(numericValue));
					} else if (line.contains("Rain Fall (One Hour)")) {
						we.setRainfallOneHour(Float.parseFloat(numericValue));
					} else if (line.contains("Max Wind Speed (Five Minutes):")) {
						we.setMaxWindSpeed(Float.parseFloat(numericValue));
					} else if (line.contains("Temperature:")) {
						we.setTemperature(Float.parseFloat(numericValue));
					} else if (line.contains("Humidity:")) {
						we.setHumidity(Integer.parseInt(numericValue));
					} else if (line.contains("Barometric Pressure:")) {
						we.setBarometricPressure(Float.parseFloat(numericValue));
					} else if (line.contains("Average Wind Speed (One Minute)")) {
						we.setAvgWindSpeed(Float.parseFloat(numericValue));
					} else if (line.contains("Rain Fall (24 Hour):")) {
						we.setRainfallTwentyFourHour(Float.parseFloat(numericValue.substring(2)));
					}
	
					if (isWeatherComplete(we)) {
						weatherRepository.save(we);
						data.add(we);
						break;
					}
				}
			}
	
			System.out.println("\n\n" + we.toString() + "\n\n");
			return we;
	
		} catch (Exception error) {
			System.out.println("\n\n " + error + " \n\n\n");
		}
		return null;
	}
}