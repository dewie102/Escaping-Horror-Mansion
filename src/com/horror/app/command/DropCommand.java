package com.horror.app.command;

import com.horror.Item;
import com.horror.app.Controller;

class DropCommand implements Command {
    @Override
    public void execute(String noun) {
        noun = noun.toLowerCase();
        Item item = Controller.getInstance().getPlayer().getInventory().getOrDefault(noun, null);

        if (item == null) {
            System.out.printf("%s does not exist in your inventory!\n", noun);
        } else {
            Controller.getInstance().getPlayer().removeItemFromInventory(noun);
            System.out.printf("%s has been removed from the inventory!\n", noun);
            Controller.getInstance().getCurrentRoom().addItem(noun, item);
        }
    }

    @Override
    public void execute() {
        Command.super.execute();
    }
}
