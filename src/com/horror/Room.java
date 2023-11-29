package com.horror;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<Direction, String> neighborMap;
    private Map<String, Item> itemMap;
    private Map<String, Enemy> enemyMap;
    private Map<String, Furniture> furnitureMap;
    
    public Room() {
        this("Default Room", "Default Description");
    }
    
    public Room(String name, String description) {
        this(name, description,
                new HashMap<>(), new HashMap<>(),
                new HashMap<>(), new HashMap<>());
    }
    
    public Room(String name, String description,
                Map<Direction, String> neighborMap, Map<String, Item> itemMap,
                Map<String, Enemy> enemyMap, Map<String, Furniture> furnitureMap) {
        this.setName(name);
        this.setDescription(description);
        this.setNeighborMap(neighborMap);
        this.setItemMap(itemMap);
        this.setEnemyMap(enemyMap);
        this.setFurnitureMap(furnitureMap);
    }
    
    public String getFullDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append(getDescription());
    
        builder.append("\n");
        
        if(!furnitureMap.isEmpty()) {
            builder.append("You see:\n");
    
            for (Map.Entry<String, Furniture> entry : getFurnitureMap().entrySet()) {
                builder.append("\t");
                builder.append(entry.getValue().getDescription());
                builder.append("\n");
            }
        }
    
        if(!itemMap.isEmpty()) {
            builder.append("There is:\n");
    
            for (Map.Entry<String, Item> entry : getItemMap().entrySet()) {
                if(!entry.getValue().isHidden()) {
                    builder.append("\t");
                    builder.append(entry.getValue().getDescription());
                    builder.append("\n");
                }
            }
        }
    
        if(!enemyMap.isEmpty()) {
            builder.append("Who's here:\n");
    
            for (Map.Entry<String, Enemy> entry : getEnemyMap().entrySet()) {
                builder.append("\t");
                builder.append(entry.getValue().getDescription());
                builder.append("\n");
            }
        }
    
        builder.append("There are doors to your:\n");
    
        for (Map.Entry<Direction, String> entry : getNeighborMap().entrySet()) {
            builder.append("\t");
            builder.append(entry.getKey());
            builder.append("\n");
        }
        
        return builder.toString();
    }
    
    public void linkHiddenItemsToFurniture() {
        for(Map.Entry<String, Item> itemEntry : itemMap.entrySet()) {
            // If the item is hidden get the hiding location
            if(itemEntry.getValue().isHidden()) {
                Furniture furniture = furnitureMap.getOrDefault(itemEntry.getValue().getHiddenLocation(), null);
                // If the location is not valid just return
                if(furniture == null) {
                    return;
                }
                
                // Add the item in the furniture to be found or gotten
                furniture.addItem(itemEntry.getKey(), itemEntry.getValue());
            }
        }
    }
    
    public void addNeighbor(Direction direction, String roomName) {
        neighborMap.put(direction, roomName);
    }
    
    public void removeNeighbor(Direction direction) {
        neighborMap.remove(direction);
    }
    
    public void addItem(String name, Item item) {
        itemMap.put(name, item);
    }
    
    public void removeItem(String name) {
        itemMap.remove(name);
    }
    
    public void addEnemy(String name, Enemy enemy) {
        enemyMap.put(name, enemy);
    }
    
    public void removeEnemy(String name) {
        enemyMap.remove(name);
    }
    
    public void addFurniture(String name, Furniture furniture) {
        furnitureMap.put(name, furniture);
    }
    
    public void removeFurniture(String name) {
        furnitureMap.remove(name);
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
    
    public Map<Direction, String> getNeighborMap() {
        return neighborMap;
    }
    
    private void setNeighborMap(Map<Direction, String> neighborMap) {
        this.neighborMap = neighborMap;
    }
    
    public Map<String, Item> getItemMap() {
        return itemMap;
    }
    
    private void setItemMap(Map<String, Item> itemMap) {
        this.itemMap = itemMap;
    }
    
    public Map<String, Enemy> getEnemyMap() {
        return enemyMap;
    }
    
    private void setEnemyMap(Map<String, Enemy> enemyMap) {
        this.enemyMap = enemyMap;
    }
    
    public Map<String, Furniture> getFurnitureMap() {
        return furnitureMap;
    }
    
    private void setFurnitureMap(Map<String, Furniture> furnitureMap) {
        this.furnitureMap = furnitureMap;
    }
}