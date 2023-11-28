package com.horror;

public class Monster extends Enemy {
    
    public Monster() {
        this("Default Name", "Default Description");
    }
    
    public Monster(String name, String description) {
        this(name, description, new Room());
    }
    
    public Monster(String name, String description, Room startRoom) {
        super(name, startRoom, description);
    }
    
    @Override
    public String toString() {
        return String.format("%s: name=%s, description=%s, currentRoom=%s",
                getClass().getSimpleName(),
                getName(),
                getDescription(),
                getCurrentRoom());
    }
}