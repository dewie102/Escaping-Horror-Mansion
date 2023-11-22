package com.horror;

import java.util.HashMap;
import java.util.Map;

class Player extends Character {
    Map<String, Item> inventory;
    
    public Player() {
        this("Default Name", new Room(), new HashMap<>());
    }
    
    public Player(String name, Room startRoom, Map<String, Item> inventory) {
        super(name, startRoom);
        this.setInventory(inventory);
    }
    
    private void setInventory(Map<String, Item> inventory) {
        this.inventory = inventory;
    }
}