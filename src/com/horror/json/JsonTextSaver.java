package com.horror.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.horror.Player;
import com.horror.Room;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class JsonTextSaver {

    private static void saveObjectToFile(Object object, String dir, String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(object);

        Path filePath = Paths.get(dir, filename);

        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println("Error creating directory: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(jsonString);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void saveRoomMapToFile(Map<String, Room> roomMap, String dir, String filename) {
        saveObjectToFile(roomMap, dir, filename);
    }

    public static void savePlayerToFile(Player player, String dir, String filename) {
        saveObjectToFile(player, dir, filename);
    }
}
