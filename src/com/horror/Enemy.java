package com.horror;

public abstract class Enemy extends Character {
    
    private String description;
    private final String type = null;
    
    public Enemy() {
        this("Default Name", new Room());
    }
    
    public Enemy(String name, Room startRoom) {
        this(name, startRoom, "Default Description");
    }
    
    public Enemy(String name, Room startRoom, String description) {
        super(name, startRoom);
        setDescription(description);
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}