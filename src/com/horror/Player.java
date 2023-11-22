package com.horror;

import java.util.HashMap;
import java.util.Map;

public class Player extends Character {
    private Map<String, Item> inventory;
    
    public Player() {
        this("Default Name", new Room(), new HashMap<>());
    }
    
    public Player(String name, Room startRoom, Map<String, Item> inventory) {
        super(name, startRoom);
        this.setInventory(inventory);
    }
    
    public Map<String, Item> getInventory() {
        return inventory;
    }
    
    private void setInventory(Map<String, Item> inventory) {
        this.inventory = inventory;
    }
    
    @Override
    public String toString() {
        return String.format("%s: name=%s, currentRoom=%s, inventory=%s",
                getClass().getSimpleName(),
                getName(),
                getCurrentRoom(),
                getInventory());
    }
}