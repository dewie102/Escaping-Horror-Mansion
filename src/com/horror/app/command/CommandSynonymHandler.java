package com.horror.app.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommandSynonymHandler {

    private static final HashMap<ActionType, HashSet<String>> actionTypeSynonyms = new HashMap<>();

    static {
        actionTypeSynonyms.put(ActionType.GO, new HashSet<>(Set.of("go", "walk", "move")));
        actionTypeSynonyms.put(ActionType.GET, new HashSet<>(Set.of("get", "grab", "pick up", "take")));
        actionTypeSynonyms.put(ActionType.LOOK, new HashSet<>(Set.of("look", "see", "view", "watch")));
        actionTypeSynonyms.put(ActionType.DROP, new HashSet<>(Set.of("drop", "fall", "leave", "put")));
        actionTypeSynonyms.put(ActionType.HELP, new HashSet<>(Set.of("help", "assist")));
        actionTypeSynonyms.put(ActionType.QUIT, new HashSet<>(Set.of("quit", "exit")));
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
