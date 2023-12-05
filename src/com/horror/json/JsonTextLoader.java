package com.horror.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.horror.Enemy;
import com.horror.Monster;
import com.horror.Player;
import com.horror.Room;
import com.horror.app.Controller;
import com.horror.app.DisplayHandler;
import com.horror.app.command.ActionType;

import java.io.*;
import java.util.*;

public class JsonTextLoader {

    private static GsonBuilder registerTypeAdapters() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Enemy.class, (JsonDeserializer<Enemy>) (json, typeOfT, context) -> {
            Gson g = new Gson();
            JsonObject enemyObj = json.getAsJsonObject();

            if (enemyObj.has("type")) {
                String type = enemyObj.get("type").getAsString();
                if ("monster".equals(type)) {
                    return g.fromJson(json, Monster.class);
                }
                // Handle other types if needed
            }

            return g.fromJson(json, Enemy.class);
        });

        return gsonBuilder;
    }
    
    public static JsonReader getJsonReader(String filepath, boolean readInsideJar) {
        if(readInsideJar) {
            InputStream inStream = Objects.requireNonNull(JsonTextLoader.class.getResourceAsStream(filepath));
            return new JsonReader(new InputStreamReader(inStream));
        } else {
            try {
                return new JsonReader(new FileReader(filepath));
            } catch(FileNotFoundException e) {
                Controller.displayHandler.displayTextClearBefore(String.format("Error loading in file: %s\n%s",
                        filepath, Arrays.toString(e.getStackTrace())), true);
                Controller.getInstance().exitGame();
                return null;
            }
        }
    }

    public static Map<String, String> loadHashMapFromFile(String filepath, boolean readInsideJar) {
        Gson gson = new Gson();
        JsonReader reader = getJsonReader(filepath, readInsideJar);
        return gson.fromJson(reader, new TypeToken<Map<String, String>>(){}.getType());
    }

    public static HashMap<ActionType, HashSet<String>> loadActionTypeSynonymsFromFile(String filepath, boolean readInsideJar) {
        Gson gson = new Gson();
        JsonReader reader = getJsonReader(filepath, readInsideJar);
        return gson.fromJson(reader, new TypeToken<HashMap<ActionType, HashSet<String>>>(){}.getType());
    }

    public static Map<String, Room> loadLevelFromFile(String filepath, boolean readInsideJar) {
        Gson gson = registerTypeAdapters().create();
        JsonReader reader = getJsonReader(filepath, readInsideJar);
        return gson.fromJson(reader, new TypeToken<Map<String, Room>>(){}.getType());
    }

    public static Player loadPlayerFromFile(String filepath, boolean readInsideJar) {
        Gson gson = registerTypeAdapters().create();
        JsonReader reader = getJsonReader(filepath, readInsideJar);
        return gson.fromJson(reader, Player.class);
    }

    public static DisplayHandler loadDisplayHandlerClass(String filepath, boolean readInsideJar) {
        Gson gson = new Gson();
        JsonReader reader = getJsonReader(filepath, readInsideJar);
        return gson.fromJson(reader, DisplayHandler.class);
    }
}