package com.weatherbackend.weatherapp;

import java.io.File;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.WeakHashMap;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

			// readFiles("src/main/resources/weather_data_autumn_2024/weather_20240829_135303.txt");
			// readFiles("src/main/resources/weather_data_autumn_2024/weather_20240829_141538.txt");
			// readFiles("src/main/resources/weather_data_autumn_2024/weather_20240829_140454.txt");
			// readJson("src/main/resources/weatherJSON/weather_20240814_103138.json");
			postTest();
		};
	}
	

	private ResponseEntity<String> postTest() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Weather content = readJson("src/main/resources/weatherJSON/weather_20240827_142839.json");
			checkAndSetDefaults(content);
			isWeatherComplete(content);
			String weatherJson = objectMapper.writeValueAsString(content);
			// weatherRepository.save(content);
			HttpClient httpClient = HttpClient.newHttpClient();
			System.out.println("Building HTTP request...");
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://localhost:8080/api/weathers"))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(weatherJson))
					.build();
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Received HTTP response: " + response.statusCode());
			System.out.println("Response body: " + response.body());
			ObjectMapper responseMapper = new ObjectMapper();
			JsonNode responseBody = responseMapper.readTree(response.body());
			System.out.println("Parsed Response: " + responseBody.toPrettyString());
			if (response.statusCode() == 200) {
				System.out.println("Response body: " + response.body());
				return ResponseEntity.ok(response.body());
			} else {
				System.out.println("Error: " + response.body());
				return ResponseEntity.status(response.statusCode()).body(response.body());
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
		return null;
	}

	private Weather readJson(String path) {
		Weather weatherData = new Weather();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			weatherData = objectMapper.readValue(new File(path), Weather.class);
			// System.out.println("Weather data from JSON: " + weatherData);
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
		return weatherData;
	}
	

	public void checkAndSetDefaults(Weather weather) {
		if (weather.getRainfallOneHour() == null) {
			weather.setRainfallOneHour(0.00f);
		}
		if (weather.getRainfallTwentyFourHour() == null) {
			weather.setRainfallTwentyFourHour(0.0f);
		}
	}
	

	private static boolean isWeatherComplete(Weather we) {
		return we.getRainfallOneHour() != null && we.getMaxWindSpeed() != null &&
				we.getTemperature() != null && we.getHumidity() != null &&
				we.getRainfallTwentyFourHour() != null
				&& we.getBarometricPressure() != null && we.getWindDirection() != null &&
				we.getAvgWindSpeed() != null;
	}

	// private Weather readFiles(String path) {
	// 	List<Weather> data = new ArrayList<>();
	// 	try {
	// 		Weather we = new Weather();
	// 		File weatherdata = new File(path);

	// 		// Try-with-resources sulkee scannerin automaattisesti
	// 		try (Scanner scanner = new Scanner(weatherdata)) {
	// 			while (scanner.hasNextLine()) {
	// 				String line = scanner.nextLine();
	// 				String numericValue = line.replaceAll("[^0-9.]", "").trim();
	// 				String name = weatherdata.getName();

	// 				we.setDate(name.substring(8, 16));
	// 				we.setTime(name.substring(17, name.length() - 4));

	// 				if (line.contains("Wind Direction")) {
	// 					we.setWindDirection(Integer.parseInt(numericValue));
	// 				} else if (line.contains("Rain Fall (One Hour)")) {
	// 					we.setRainfallOneHour(Float.parseFloat(numericValue));
	// 				} else if (line.contains("Max Wind Speed (Five Minutes):")) {
	// 					we.setMaxWindSpeed(Float.parseFloat(numericValue));
	// 				} else if (line.contains("Temperature:")) {
	// 					we.setTemperature(Float.parseFloat(numericValue));
	// 				} else if (line.contains("Humidity:")) {
	// 					we.setHumidity(Integer.parseInt(numericValue));
	// 				} else if (line.contains("Barometric Pressure:")) {
	// 					we.setBarometricPressure(Float.parseFloat(numericValue));
	// 				} else if (line.contains("Average Wind Speed (One Minute)")) {
	// 					we.setAvgWindSpeed(Float.parseFloat(numericValue));
	// 				} else if (line.contains("Rain Fall (24 Hour):")) {
	// 					we.setRainfallTwentyFourHour(Float.parseFloat(numericValue.substring(2)));
	// 				}
	// 				if (isWeatherComplete(we)) {
	// 					weatherRepository.save(we);
	// 					data.add(we);
	// 					break;
	// 				}
	// 			}
	// 		}
	// 		System.out.println("\n\n" + we.toString() + "\n\n");
	// 		return we;

	// 	} catch (Exception error) {
	// 		System.out.println("\n\n " + error + " \n\n\n");
	// 	}
	// 	return null;
	// }

	// private ResponseEntity<String> postTest2() {
	// 	ObjectMapper objectMapper = new ObjectMapper();
	// 	try {
	// 		Weather content = readFiles("src/main/resources/weather_data_autumn_2024/weather_20240829_140454.txt");
	// 		String weatherJson = objectMapper.writeValueAsString(content);
	// 		HttpClient httpClient = HttpClient.newHttpClient();
	// 		HttpRequest request = HttpRequest.newBuilder()
	// 				.uri(URI.create("http://localhost:8080/api/weathers"))
	// 				.POST(BodyPublishers.ofString(weatherJson))
	// 				.build();

	// 		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	// 		System.out.println("OKKKKK" + response.toString());
	// 		return ResponseEntity.ok(response.body());
	// 	} catch (Exception e) {
	// 		System.out.println("ERROR: " + e);
	// 	}
	// 	return null;
	// }
}
