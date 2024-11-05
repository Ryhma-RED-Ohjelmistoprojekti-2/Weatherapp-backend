package com.weatherbackend.weatherapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherappApplication.class, args);
	}

// 	@Bean
// 	CommandLineRunner test() {
// 		return (args) -> {
// 			postTest();
// 		};
// 	}
// private ResponseEntity<String> postTest() {
//     try {
//         String filePath = FolderWatcher.getNewestFilePath("src/main/resources/weatherJSON/");
//         if (filePath == null) {
//             System.out.println("No files found in the directory.");
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No files found in directory");
//         }
// 		System.out.println(filePath);
//         Weather content = readJson(filePath);
//         checkAndSetDefaults(content);
//         isWeatherComplete(content);

//         ObjectMapper objectMapper = new ObjectMapper();
//         String weatherJson = objectMapper.writeValueAsString(content);

//         HttpClient httpClient = HttpClient.newHttpClient();
//         HttpRequest request = HttpRequest.newBuilder()
//                 .uri(URI.create("http://localhost:8080/api/weathers"))
//                 .header("Content-Type", "application/json")
//                 .POST(HttpRequest.BodyPublishers.ofString(weatherJson))
//                 .build();

//         HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

//         System.out.println("Received HTTP response: " + response.statusCode());
//         System.out.println("Response body: " + response.body());

//         if (response.statusCode() == 200) {
//             return ResponseEntity.ok(response.body());
//         } else {
//             return ResponseEntity.status(response.statusCode()).body(response.body());
//         }
//     } catch (Exception e) {
//         System.err.println("ERROR: " + e.getMessage());
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
//     }
// }



// 	private Weather readJson(String path) {
// 		Weather weatherData = new Weather();
// 		ObjectMapper objectMapper = new ObjectMapper();
// 		try {
// 			weatherData = objectMapper.readValue(new File(path), Weather.class);
// 		} catch (Exception e) {
// 			System.err.println("Error: " + e);
// 		}
// 		return weatherData;
// 	}


// 	public void checkAndSetDefaults(Weather weather) {
// 		if (weather.getRainfallOneHour() == null) {
// 			weather.setRainfallOneHour(0.00f);
// 		}
// 		if (weather.getRainfallTwentyFourHour() == null) {
// 			weather.setRainfallTwentyFourHour(0.0f);
// 		}
// 	}

	
// 	private static boolean isWeatherComplete(Weather we) {
// 		return we.getRainfallOneHour() != null && we.getMaxWindSpeed() != null &&
// 				we.getTemperature() != null && we.getHumidity() != null &&
// 				we.getRainfallTwentyFourHour() != null
// 				&& we.getBarometricPressure() != null && we.getWindDirection() != null &&
// 				we.getAvgWindSpeed() != null;
// 	}
}
