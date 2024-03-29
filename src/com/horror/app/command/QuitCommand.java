package com.horror.app.command;

import com.horror.app.Controller;
import com.horror.app.InputHandler;

class QuitCommand implements Command{
    @Override
    public String execute(String noun) {
        return ActionType.QUIT.getErrorMessage();
    }

    @Override
    public String execute() {
        Controller.getInstance().setGameOver(InputHandler.confirmQuit());
        return "";
    }
}
