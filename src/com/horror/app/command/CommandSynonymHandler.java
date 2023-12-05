package com.horror.app.command;

import com.horror.app.Controller;
import com.horror.json.JsonTextLoader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CommandSynonymHandler {

    private static HashMap<ActionType, HashSet<String>> actionTypeSynonyms = null;

    static {
        actionTypeSynonyms = JsonTextLoader.loadActionTypeSynonymsFromFile("/Synonyms.json",
                Controller.getInstance().readInsideJar);
    }

    public static HashMap<ActionType, Integer> getActionDetails(String action) {
        String lowercaseAction = action.toLowerCase();
        HashMap<ActionType, Integer> result = new HashMap<>();

        for (Map.Entry<ActionType, HashSet<String>> entry : actionTypeSynonyms.entrySet()) {
            for (String synonym : entry.getValue()) {
                if (lowercaseAction.startsWith(synonym)) {
                    result.put(entry.getKey(), synonym.length());
                }
            }
        }
        return result;
    }
}
