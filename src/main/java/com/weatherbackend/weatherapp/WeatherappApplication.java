package com.weatherbackend.weatherapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.core.env.Environment;

import com.weatherbackend.weatherapp.domain.Weather;

@SpringBootApplication
public class WeatherappApplication {

	private final Environment environment;

	public WeatherappApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(WeatherappApplication.class, args);
	}

	// NOTE! null folderPath causes program to crash!
	@Bean
	CommandLineRunner test() {
		return (args) -> {
		// // Path to the folder containing your .txt files
		// String folderPath = environment.getProperty("FILE_PATH");

		// // Get all .txt files in the folder
		// try (Stream<File> files = Files.list(Paths.get(folderPath)).map(Path::toFile)
		// .filter(file -> file.getName().endsWith(".txt"))) {
		// files.forEach(file -> {
		// // Extract the date from the file name
		// String fileName = file.getName();
		// String date = fileName.substring(fileName.indexOf('_') + 1,
		// fileName.lastIndexOf('.'));
		// System.out.println("File Date: " + date);
		// });
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// };
		// }
		List<Weather> data = new ArrayList<>();
		try {
			Weather we = new Weather();
			File weatherData = new File("src/main/resources/weather_data_autumn_2024/weather_20240829_135409.txt");
			Scanner scanner = new Scanner(weatherData);
			while (scanner.hasNextLine()) {
				String res = scanner.nextLine();
				String numericValue = res.replaceAll("[^0-9.]", "").trim();
                if (res.contains("Wind Direction")) {
                    we.setWindDirection(Integer.parseInt(numericValue));
                } else if (res.contains("Rain Fall (One Hour)")) {
                    we.setRainfallOneHour(Float.parseFloat(numericValue));
                } else if (res.contains("Max Wind Speed (Five Minutes):")) {
                    we.setMaxWindSpeed(Float.parseFloat(numericValue));
                } else if (res.contains("Temperature:")) {
                    we.setTemperature(Float.parseFloat(numericValue));
                } else if (res.contains("Humidity:")) {
                    we.setHumidity(Integer.parseInt(numericValue));
                } else if (res.contains("Barometric Pressure:")) {
                    we.setBarometricPressure(Float.parseFloat(numericValue));
                } else if (res.contains("Average Wind Speed (One Minute)")) {
                    we.setAvgWindSpeed(Float.parseFloat(numericValue));
                } else if (res.contains("Rain Fall (24 Hour):")) {
                    we.setRainfallTwentyFourHour(Float.parseFloat(numericValue));
                }
				if (isWeatherComplete(we)) {
                    data.add(we);
                    break;
                }
			}
			System.out.println("\n\n"+data.toString() + "\n\n");
		} catch (Exception error) {
			System.out.println("\n\n " + error + " \n\n\n");
		}};
	}
	private static boolean isWeatherComplete(Weather we) {
		return we.getRainfallOneHour() != null && we.getMaxWindSpeed() != null &&
		we.getTemperature() != null && we.getHumidity() != null && we.getRainfallTwentyFourHour() != null && we.getBarometricPressure() != null && we.getWindDirection() != null && we.getAvgWindSpeed() != null;
	}
};