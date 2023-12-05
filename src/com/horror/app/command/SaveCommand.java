package com.horror.app.command;

import com.horror.app.Controller;

class SaveCommand implements Command{
    @Override
    public String execute(String noun) {
        return ActionType.SAVE.getErrorMessage();
    }

    @Override
    public String execute() {
        Controller.getInstance().saveGame();
        return "Game has been saved!";
    }
}
