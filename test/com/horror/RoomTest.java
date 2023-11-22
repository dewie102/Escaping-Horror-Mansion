package com.horror;

import org.junit.Test;
import org.junit.Before;

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
}