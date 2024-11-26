package com.weatherbackend.weatherapp;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.Optional;

public class FolderWatcher {

    
    public static Path getNewestFile(Path directory) throws IOException {
        Optional<Path> newestFile = Files.list(directory)
            .filter(Files::isRegularFile)
            .max(Comparator.comparingLong(path -> path.toFile().lastModified()));

        return newestFile.orElse(null);
    }

    // parametrinä kansion polku => palauttaa uusimman tiedoston polun stringinä
    public static String getNewestFilePath(String directoryPath) {
        Path path = Paths.get(directoryPath);
        try {
            Path newestFile = getNewestFile(path);
            if (newestFile != null && newestFile.toString().endsWith(".json")) {
                System.out.println("Newest file detected: " + newestFile);
                return newestFile.toString();
            }
        } catch (IOException e) {
            System.err.println("Error while retrieving the newest file: " + e.getMessage());
        }
        return null;
    }
}
