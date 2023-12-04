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
    public static void saveRoomMapToFile(Map<String, Room> roomMap, String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(roomMap);

        Path filePath = Paths.get("resources", filename);
        System.out.println(filePath);
        System.out.println(System.getProperty("user.dir"));

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(jsonString);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void savePlayerToFile(Player player, String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(player);

        Path filePath = Paths.get("resources", filename);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(jsonString);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


