package com.weatherbackend.weatherapp;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.Optional;

public class WeatherTxtLocator {

    public static Path getNewest(Path directory) throws IOException {
        Optional<Path> newestFile = Files.list(directory)
            .filter(Files::isRegularFile)
            .max(Comparator.comparingLong(path -> path.toFile().lastModified()));

        return newestFile.orElse(null);
    }

    public static String getWeatherTxtPath(String directory) {
        try {
            Path path = Paths.get(directory);
            Path newest = getNewest(path);
            if (newest != null && newest.getFileName().toString().matches("^weather_\\d{8}_\\d{6}\\.txt$")) {
                System.out.println("Newest record found: " + newest);
                return newest.toString();
            }
        } catch (IOException e) {
            System.err.println("Error while retrieving the newest weather_--------_------.txt: " + e.getMessage());
        }
        return null;
    }
}
