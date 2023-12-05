package com.horror;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player extends Character {
    private Map<String, Item> inventory;
    
    public Player() {
        this("Default Name", new Room(), new HashMap<>());
    }
    
    public Player(String name, Room startRoom, Map<String, Item> inventory) {
        super(name, startRoom);
        this.setInventory(inventory);
    }
    
    public void goTo(Room room) {
        if(room != null) {
            setCurrentRoom(room);
        }
    }
    
    public String getInventoryDisplayString() {
        String inventoryString = "Inventory: [ " +
                String.join(", ", inventory.keySet()) +
                " ]\n";
    
        return inventoryString;
    }
    
    public Map<String, Item> getInventory() {
        return inventory;
    }
    
    private void setInventory(Map<String, Item> inventory) {
        // If not null assign it, otherwise assign a new hashmap
        this.inventory = Objects.requireNonNullElseGet(inventory, HashMap::new);
    }

    public void addItemToInventory(String itemName, Item item) {
        getInventory().put(itemName, item);
    }

    public void removeItemFromInventory(String itemName) {
        getInventory().remove(itemName);
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