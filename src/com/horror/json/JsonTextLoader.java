package com.horror.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.horror.Enemy;
import com.horror.Monster;
import com.horror.Room;
import com.horror.app.command.ActionType;
import com.horror.app.command.CommandSynonymHandler;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class JsonTextLoader {
    public static Map<String, String> loadHashMapFromFile(String filepath) {
        Map<String, String> result;
        
        Gson gson = new Gson();
        InputStream inStream = Objects.requireNonNull(JsonTextLoader.class.getResourceAsStream(filepath));
        JsonReader reader =
                new JsonReader(new InputStreamReader(inStream));
        
        result = gson.fromJson(reader, new TypeToken<Map<String, String>>(){}.getType());
        
        return result;
    }

    public static HashMap<ActionType, HashSet<String>> loadActionTypeSynonymsFromFile(String filepath) {
        HashMap<ActionType, HashSet<String>> result;

        Gson gson = new Gson();
        InputStream inStream = Objects.requireNonNull(JsonTextLoader.class.getResourceAsStream(filepath));
        JsonReader reader = new JsonReader(new InputStreamReader(inStream));

        result = gson.fromJson(reader, new TypeToken<HashMap<ActionType, HashSet<String>>>(){}.getType());

        return result;
    }
    
    public static Map<String, Room> loadLevelFromFile(String filepath) {

        Map<String, Room> rooms;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Enemy.class, (JsonDeserializer<Enemy>) (json, typeOfT, context) -> {
            Gson g = new Gson();
            JsonObject enemyObj = json.getAsJsonObject();

            if (enemyObj.has("@type")) {
                String type = enemyObj.get("@type").getAsString();
                if ("monster".equals(type)) {
                    return g.fromJson(json, Monster.class);
                }
                // ghosts
            }

            return g.fromJson(json, Enemy.class);
        });

        Gson gson = gsonBuilder.create();
        InputStream inStream = Objects.requireNonNull(JsonTextLoader.class.getResourceAsStream(filepath));
        JsonReader reader =
                new JsonReader(new InputStreamReader(inStream));

        rooms = gson.fromJson(reader, new TypeToken<Map<String, Room>>(){}.getType());
        return rooms;
    }
}