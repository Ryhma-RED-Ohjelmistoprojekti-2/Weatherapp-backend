package com.weatherbackend.weatherapp;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherbackend.weatherapp.domain.Weather;

@SpringBootApplication
public class WeatherappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherappApplication.class, args);
	}

	// @Bean
	// CommandLineRunner test() {
	// 	return (args) -> {
	// 		postTest();
	// 	};
	// }

	// private ResponseEntity<String> postTest() {
	// 	try {
	// 		ObjectMapper objectMapper = new ObjectMapper();
	// 		Weather content = readJson("src/main/resources/weatherJSON/weather_20240827_142839.json");
	// 		checkAndSetDefaults(content);
	// 		isWeatherComplete(content);
	// 		String weatherJson = objectMapper.writeValueAsString(content);

	// 		HttpClient httpClient = HttpClient.newHttpClient();

	// 		System.out.println("Building HTTP request...");
	// 		HttpRequest request = HttpRequest.newBuilder()
	// 				.uri(URI.create("http://localhost:8080/api/weathers"))
	// 				.header("Content-Type", "application/json")
	// 				.POST(HttpRequest.BodyPublishers.ofString(weatherJson))
	// 				.build();

	// 		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	// 		System.out.println("Received HTTP response: " + response.statusCode());
	// 		System.out.println("Response body: " + response.body());

	// 		ObjectMapper responseMapper = new ObjectMapper();
	// 		JsonNode responseBody = responseMapper.readTree(response.body());
	// 		System.out.println("Parsed Response: " + responseBody.toPrettyString());

	// 		if (response.statusCode() == 200) {
	// 			System.out.println("Response body: " + response.body());
	// 			return ResponseEntity.ok(response.body());
	// 		} else {
	// 			System.out.println("Error: " + response.body());
	// 			return ResponseEntity.status(response.statusCode()).body(response.body());
	// 		}
	// 	} catch (Exception e) {
	// 		System.out.println("ERROR: " + e);
	// 	}
	// 	return null;
	// }


	// private Weather readJson(String path) {
	// 	Weather weatherData = new Weather();
	// 	ObjectMapper objectMapper = new ObjectMapper();
	// 	try {
	// 		weatherData = objectMapper.readValue(new File(path), Weather.class);
	// 	} catch (Exception e) {
	// 		System.err.println("Error: " + e);
	// 	}
	// 	return weatherData;
	// }


	// public void checkAndSetDefaults(Weather weather) {
	// 	if (weather.getRainfallOneHour() == null) {
	// 		weather.setRainfallOneHour(0.00f);
	// 	}
	// 	if (weather.getRainfallTwentyFourHour() == null) {
	// 		weather.setRainfallTwentyFourHour(0.0f);
	// 	}
	// }

	
	// private static boolean isWeatherComplete(Weather we) {
	// 	return we.getRainfallOneHour() != null && we.getMaxWindSpeed() != null &&
	// 			we.getTemperature() != null && we.getHumidity() != null &&
	// 			we.getRainfallTwentyFourHour() != null
	// 			&& we.getBarometricPressure() != null && we.getWindDirection() != null &&
	// 			we.getAvgWindSpeed() != null;
	// }
}
