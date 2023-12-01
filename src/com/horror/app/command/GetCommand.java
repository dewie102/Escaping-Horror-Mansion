package com.horror.app.command;

import com.horror.Item;
import com.horror.Lookable;
import com.horror.app.Controller;

class GetCommand implements Command {
    @Override
    public String execute(String noun) {
        noun = noun.toLowerCase();
        Item item = Controller.getInstance().getCurrentRoom().getItemMap().getOrDefault(noun, null);
        Lookable furniture = Controller.getInstance().getCurrentRoom().getFurnitureMap().getOrDefault(noun, null);

        if ((item == null && furniture == null) || (item != null && item.isHidden())) {
            return String.format("Where do you see %s?\n", noun);
        } else {
            if (item != null) {
                // If an item, add to the inventory
                Controller.getInstance().getPlayer().addItemToInventory(noun, item);
                Controller.getInstance().getCurrentRoom().removeItem(noun);
                return String.format("%s has been added to the inventory!\n", noun);
            } else {
                // If furniture, print a message that it can't be picked up
                return String.format("You can't pick up %s. It's part of the room.\n", noun);
            }
        }
    }

    @Override
    public String execute() {
        return "Please enter command with an argument, example: get [item]. Type `help` to list commands";
    }
}

