package com.horror.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class JsonTextLoader {
    public static Map<String, String> loadHashMapFromFile(String filepath) {
        Map<String, String> result = new HashMap<>();
        
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(Path.of(filepath).toFile()));
            
            result = gson.fromJson(reader, new TypeToken<Map<String, String>>(){}.getType());
        } catch(FileNotFoundException e) {
            System.out.printf("File not found: %s\n", e);
        }
        
        return result;
    }
}