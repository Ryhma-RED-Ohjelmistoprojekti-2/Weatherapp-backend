package com.weatherbackend.weatherapp;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

public class WeatherTxtLocator {

    private static final DateTimeFormatter FILE_NAME_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public static LocalDateTime extractTimestampFromFilename(Path path) {
        Pattern pattern = Pattern.compile("^weather_(\\d{8})_(\\d{6})\\.txt$");
        Matcher matcher = pattern.matcher(path.getFileName().toString());

        if (matcher.matches()) {
            String dateStr = matcher.group(1);
            String timeStr = matcher.group(2);
            String datetimeStr = dateStr + "_" + timeStr;
            return LocalDateTime.parse(datetimeStr, FILE_NAME_DATE_FORMATTER);
        }
        return null;
    }

    public static Path getNewest(Path directory) throws IOException {
        Optional<Path> newestFile = Files.list(directory)
            .filter(Files::isRegularFile)
            .filter(path -> extractTimestampFromFilename(path) != null)  
            .max(Comparator.comparing(path -> extractTimestampFromFilename(path)));  

        return newestFile.orElse(null); 
    }

    public static String getWeatherTxtPath(String directory) {
        Path path = Paths.get(directory);
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            System.err.println("Invalid directory path: " + directory);
            return null;
        }
        try {
            Path newest = getNewest(path);
            if (newest != null) {
                System.out.println("Newest record found: " + newest);
                return newest.toString();
            }
        } catch (IOException e) {
            System.err.println("Error while retrieving the newest weather_--------_------.txt: " + e.getMessage());
        }
        System.out.println("No valid weather file found in directory: " + directory);
        return null;
    }
}
