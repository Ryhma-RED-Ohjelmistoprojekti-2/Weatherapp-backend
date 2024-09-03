package com.weatherbackend.weatherapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
public class WeatherappApplication {

	private final Environment environment;

	public WeatherappApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(WeatherappApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			// Path to the folder containing your .txt files
			String folderPath = environment.getProperty("FILE_PATH");

			// Get all .txt files in the folder
			try (Stream<File> files = Files.list(Paths.get(folderPath)).map(Path::toFile)
					.filter(file -> file.getName().endsWith(".txt"))) {
				files.forEach(file -> {
					// Extract the date from the file name
					String fileName = file.getName();
					String date = fileName.substring(fileName.indexOf('_') + 1, fileName.lastIndexOf('.'));
					System.out.println("File Date: " + date);
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
}
