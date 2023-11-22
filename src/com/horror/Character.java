package com.horror;

public abstract class Character {
    private String name;
    private Room currentRoom;
    
    public Character(String name, Room startRoom) {
        this.name = name;
        this.currentRoom = startRoom;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}