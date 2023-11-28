package com.horror.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.horror.Room;

import java.io.InputStream;
import java.io.InputStreamReader;
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
    
    public static Map<String, Room> loadLevelFromFile(String filepath) {
        Map<String, Room> rooms;
        
        Gson gson = new Gson();
        InputStream inStream = Objects.requireNonNull(JsonTextLoader.class.getResourceAsStream(filepath));
        JsonReader reader =
                new JsonReader(new InputStreamReader(inStream));
        
        rooms = gson.fromJson(reader, new TypeToken<Map<String, Room>>(){}.getType());

        
        return rooms;
    }
}