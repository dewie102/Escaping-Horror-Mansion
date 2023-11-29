package com.horror;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<Direction, String> neighbors;
    private Map<String, Item> items;
    private Map<String, Enemy> enemies;
    private Map<String, Furniture> furniture;
    
    public Room() {
        this("Default Room", "Default Description");
    }
    
    public Room(String name, String description) {
        this(name, description,
                new HashMap<Direction, String>(), new HashMap<String, Item>(),
                new HashMap<String, Enemy>(),new HashMap<String, Furniture>());
    }
    
    public Room(String name, String description,
                Map<Direction, String> neighbors, Map<String, Item> items,
                Map<String, Enemy> enemies, Map<String, Furniture> furniture) {
        this.setName(name);
        this.setDescription(description);
        this.setNeighbors(neighbors);
        this.setItems(items);
        this.setEnemies(enemies);
        this.setFurniture(furniture);
    }
    
    public String getFullDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append(getDescription());
    
        builder.append("\n");
        
        if(!furniture.isEmpty()) {
            builder.append("You see:\n");
    
            for (Map.Entry<String, Furniture> entry : getFurniture().entrySet()) {
                builder.append("\t");
                builder.append(entry.getValue().getDescription());
                builder.append("\n");
            }
        }
    
        if(!items.isEmpty()) {
            builder.append("There is:\n");
    
            for (Map.Entry<String, Item> entry : getItems().entrySet()) {
                builder.append("\t");
                builder.append(entry.getValue().getDescription());
                builder.append("\n");
            }
        }
    
        if(!enemies.isEmpty()) {
            builder.append("Who's here:\n");
    
            for (Map.Entry<String, Enemy> entry : getEnemies().entrySet()) {
                builder.append("\t");
                builder.append(entry.getValue().getDescription());
                builder.append("\n");
            }
        }
    
        builder.append("There are doors to your:\n");
    
        for (Map.Entry<Direction, String> entry : getNeighbors().entrySet()) {
            builder.append("\t");
            builder.append(entry.getKey());
            builder.append("\n");
        }
        
        return builder.toString();
    }
    
    public void addNeighbor(Direction direction, String roomName) {
        neighbors.put(direction, roomName);
    }
    
    public void removeNeighbor(Direction direction) {
        neighbors.remove(direction);
    }
    
    public void addItem(String name, Item item) {
        items.put(name, item);
    }
    
    public void removeItem(String name) {
        items.remove(name);
    }
    
    public void addEnemy(String name, Enemy enemy) {
        enemies.put(name, enemy);
    }
    
    public void removeEnemy(String name) {
        enemies.remove(name);
    }
    
    public void addFurniture(String name, Furniture furn) {
        furniture.put(name, furn);
    }
    
    public void removeFurniture(String name) {
        furniture.remove(name);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Map<Direction, String> getNeighbors() {
        return neighbors;
    }
    
    private void setNeighbors(Map<Direction, String> neighbors) {
        this.neighbors = neighbors;
    }
    
    public Map<String, Item> getItems() {
        return items;
    }
    
    private void setItems(Map<String, Item> items) {
        this.items = items;
    }
    
    public Map<String, Enemy> getEnemies() {
        return enemies;
    }
    
    private void setEnemies(Map<String, Enemy> enemies) {
        this.enemies = enemies;
    }
    
    public Map<String, Furniture> getFurniture() {
        return furniture;
    }
    
    private void setFurniture(Map<String, Furniture> furniture) {
        this.furniture = furniture;
    }
}