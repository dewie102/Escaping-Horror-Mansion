package com.horror;

import java.util.HashMap;
import java.util.Map;

public class Furniture {
    private String name;
    private String description;
    private Map<String, Item> items;
    
    public Furniture() {
        this("Default Name", "Default Description", new HashMap<>());
    }
    
    public Furniture(String name, String description, Map<String, Item> items) {
        setName(name);
        setDescription(description);
        setItems(items);
    }
    
    public void addItem(String name, Item item) {
        items.put(name, item);
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
    
    public Map<String, Item> getItems() {
        return items;
    }
    
    private void setItems(Map<String, Item> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return String.format("%s: name=%s, description=%s, items=%s",
                getClass().getSimpleName(),
                getName(),
                getDescription(),
                getItems());
    }
}