package com.horror;

import org.junit.Test;
import org.junit.Before;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RoomTest {
    
    Room testRoom;
    
    @Before
    public void setup() {
        testRoom = new Room();
    }
    
    @Test
    public void add_item_successfully_adds_item() {
        testRoom.addItem("test", new Item());
        
        Item testItem = testRoom.getItems().get("test");
        assertNotNull(testItem);
    }
    
    @Test
    public void default_constructor_adds_default_values() {
        assertEquals("Default Room", testRoom.getName());
        assertEquals("Default Description", testRoom.getDescription());
        assertEquals(new HashMap<String, Item>(), testRoom.getItems());
        assertEquals(new HashMap<String, Enemy>(), testRoom.getEnemies());
        assertEquals(new HashMap<String, Furniture>(), testRoom.getFurniture());
        assertEquals(new HashMap<String, Room>(), testRoom.getNeighbors());
    }
}