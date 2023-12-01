package com.horror.app.command;

import com.horror.Item;
import com.horror.app.Controller;

class DropCommand implements Command {
    @Override
    public String execute(String noun) {
        noun = noun.toLowerCase();
        Item item = Controller.getInstance().getPlayer().getInventory().getOrDefault(noun, null);

        if (item == null) {
            return String.format("%s does not exist in your inventory!\n", noun);
        } else {
            Controller.getInstance().getPlayer().removeItemFromInventory(noun);
            Controller.getInstance().getCurrentRoom().addItem(noun, item);
            return String.format("%s has been removed from the inventory!\n", noun);
        }
    }

    @Override
    public String execute() {
        return "Please enter command with an argument, example: drop [item]. Type `help` to list commands";
    }
}
