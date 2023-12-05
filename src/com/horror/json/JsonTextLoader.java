package com.horror.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.horror.Enemy;
import com.horror.Monster;
import com.horror.Player;
import com.horror.Room;
import com.horror.app.DisplayHandler;
import com.horror.app.command.ActionType;
import com.horror.app.command.CommandSynonymHandler;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class JsonTextLoader {

    private static GsonBuilder registerTypeAdapters() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Enemy.class, (JsonDeserializer<Enemy>) (json, typeOfT, context) -> {
            Gson g = new Gson();
            JsonObject enemyObj = json.getAsJsonObject();

            if (enemyObj.has("type")) {
                String type = enemyObj.get("type").getAsString();
                if ("monster".equals(type)) {
                    System.out.println("Yes, it's a monster");
                    return g.fromJson(json, Monster.class);
                }
                // Handle other types if needed
            }

            return g.fromJson(json, Enemy.class);
        });

        return gsonBuilder;
    }

    public static Map<String, String> loadHashMapFromFile(String filepath) {
        Gson gson = new Gson();
        InputStream inStream = Objects.requireNonNull(JsonTextLoader.class.getResourceAsStream(filepath));
        JsonReader reader = new JsonReader(new InputStreamReader(inStream));
        return gson.fromJson(reader, new TypeToken<Map<String, String>>(){}.getType());
    }

    public static HashMap<ActionType, HashSet<String>> loadActionTypeSynonymsFromFile(String filepath) {
        Gson gson = new Gson();
        InputStream inStream = Objects.requireNonNull(JsonTextLoader.class.getResourceAsStream(filepath));
        JsonReader reader = new JsonReader(new InputStreamReader(inStream));
        return gson.fromJson(reader, new TypeToken<HashMap<ActionType, HashSet<String>>>(){}.getType());
    }

    public static Map<String, Room> loadLevelFromFile(String filepath) {
        Gson gson = registerTypeAdapters().create();
        InputStream inStream = Objects.requireNonNull(JsonTextLoader.class.getResourceAsStream(filepath));
        JsonReader reader = new JsonReader(new InputStreamReader(inStream));
        return gson.fromJson(reader, new TypeToken<Map<String, Room>>(){}.getType());
    }

    public static Player loadPlayerFromFile(String filepath) {
        Gson gson = registerTypeAdapters().create();
        InputStream inStream = Objects.requireNonNull(JsonTextLoader.class.getResourceAsStream(filepath));
        JsonReader reader = new JsonReader(new InputStreamReader(inStream));
        return gson.fromJson(reader, Player.class);
    }

    public static DisplayHandler loadDisplayHandlerClass(String filepath) {
        Gson gson = new Gson();
        InputStream inStream = Objects.requireNonNull(JsonTextLoader.class.getResourceAsStream(filepath));
        JsonReader reader = new JsonReader(new InputStreamReader(inStream));

        return gson.fromJson(reader, DisplayHandler.class);
    }
}