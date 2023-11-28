package com.horror.app.command;

import com.horror.Lookable;
import com.horror.app.Controller;

import java.util.Objects;

class LookCommand implements Command{
    @Override
    public void execute(String noun) {
        noun = noun.toLowerCase();
        // Bad implementation to check items and furniture, maybe somehow check lookable items?
        Lookable item = Controller.getInstance().getCurrentRoom().getItems().getOrDefault(noun, null);
        Lookable furniture = Controller.getInstance().getCurrentRoom().getFurniture().getOrDefault(noun, null);
        
        if(item == null && furniture == null) {
            System.out.printf("Where do you see %s?\n", noun);
        } else {
            // Checks from left to right, first non-null object calls the lookAt() method
            Objects.requireNonNullElse(item, furniture).lookAt();
        }
    }

    @Override
    public void execute() {
        //System.out.println("Please enter command with an argument, example: look chair. Type `help` to list
        // commands");
        Controller.getInstance().printCurrentRoomDescription();
    }
}
