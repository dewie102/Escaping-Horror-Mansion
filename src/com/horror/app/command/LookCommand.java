package com.horror.app.command;

import com.horror.Lookable;
import com.horror.app.Controller;

import java.util.Objects;

class LookCommand implements Command{
    @Override
    public String execute(String noun) {
        noun = noun.toLowerCase();
        // Bad implementation to check items and furniture, maybe somehow check lookable items?
        Lookable item = Controller.getInstance().getCurrentRoom().getItemMap().getOrDefault(noun, null);
        Lookable furniture = Controller.getInstance().getCurrentRoom().getFurnitureMap().getOrDefault(noun, null);
        
        if(item == null && furniture == null) {
            return String.format("Where do you see %s?\n", noun);
        } else {
            // Checks from left to right, first non-null object calls the lookAt() method
            return Objects.requireNonNullElse(item, furniture).lookAt();
        }
    }

    @Override
    public String execute() {
        return "Please enter command with an argument, example: look [item]. Type `help` to list commands";
    }
}
