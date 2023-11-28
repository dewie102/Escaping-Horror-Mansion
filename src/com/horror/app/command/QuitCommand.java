package com.horror.app.command;

import com.horror.app.Controller;
import com.horror.app.InputHandler;

class QuitCommand implements Command{
    @Override
    public void execute(String noun) {
        System.out.println("Please enter command `quit` without any argument, Type `help` to list commands");
    }

    @Override
    public void execute() {
        Controller.getInstance().setGameOver(InputHandler.confirmQuit());
    }
}
