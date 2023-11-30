package com.horror;

import java.util.HashMap;
import java.util.Map;

public class Furniture implements Lookable {
    private String name;
    private String description;
    private String lookAtDescription;
    private Map<String, Item> items;
    
    public Furniture() {
        this("Default Name", "Default Description", new HashMap<>());
    }
    
    public Furniture(String name, String description, Map<String, Item> items) {
        setName(name);
        setDescription(description);
        setItems(items);
    }
    
    @Override
    public void lookAt() {
        if(lookAtDescription == null) {
            System.out.println(description);
        } else {
            System.out.println(lookAtDescription);
        }
        
        StringBuilder itemList = null;
        
        for(Map.Entry<String, Item> itemEntry : items.entrySet()) {
            if(itemEntry.getValue().isHidden()) {
                if(itemList == null) {
                    itemList = new StringBuilder();
                    itemList.append(String.format("Inside the %s you find:\n", getName()));
                }
                itemEntry.getValue().setHidden(false);
                itemList.append(String.format("\t- %s", itemEntry.getValue().getName()));
            }
        }
        
        if(itemList != null) {
            System.out.println(itemList);
        }
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