package com.horror.app.command;

import com.horror.app.Controller;

class SaveCommand implements Command{
    @Override
    public String execute(String noun) {
        return "Please enter command `save` without any argument, Type `help` to list commands";
    }

    @Override
    public String execute() {
        Controller.getInstance().saveGame();
        return "Game has been saved!";
    }
}
