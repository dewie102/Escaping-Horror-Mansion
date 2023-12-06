package com.horror.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.horror.Player;
import com.horror.Room;
import com.horror.app.Controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class JsonTextSaver {

    private static void saveObjectToFile(Object object, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(object);

        Path filePath = Paths.get(fileName);

        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            Controller.displayHandler.displayTextClearBefore("Error creating directory: " + e.getMessage(), false);
            return;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(jsonString);
        } catch (IOException e) {
            Controller.displayHandler.displayTextClearBefore("Error writing to file: " + e.getMessage(), false);
        }
    }

    public static void saveRoomMapToFile(Map<String, Room> roomMap, String fileName) {
        saveObjectToFile(roomMap, fileName);
    }

    public static void savePlayerToFile(Player player,String fileName) {
        saveObjectToFile(player, fileName);
    }
}
