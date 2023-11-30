package com.horror.app.command;

import com.horror.Lookable;
import com.horror.app.Controller;

class GetCommand implements Command {
    @Override
    public void execute(String noun) {
        noun = noun.toLowerCase();
        Lookable item = Controller.getInstance().getCurrentRoom().getItemMap().getOrDefault(noun, null);
        Lookable furniture = Controller.getInstance().getCurrentRoom().getFurnitureMap().getOrDefault(noun, null);

        if (item == null && furniture == null) {
            System.out.printf("Where do you see %s?\n", noun);
        } else {
            if (item != null) {
                // If an item, add to the inventory
                Controller.getInstance().getPlayer().addItemToInventory(noun, item);
                System.out.printf("%s has been added to the inventory!\n", noun);
                Controller.getInstance().getCurrentRoom().removeItem(noun);
            } else {
                // If furniture, print a message that it can't be picked up
                System.out.printf("You can't pick up %s. It's part of the room.\n", noun);
            }
        }
    }

    @Override
    public void execute() {
        Command.super.execute();
    }
}

