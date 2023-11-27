package com.horror.app;

class QuitCommand implements Command{
    @Override
    public void execute(String noun) {
        System.out.println("Please enter command `quit` without any argument, Type `help` to list commands");
    }

    @Override
    public void execute() {
        InputHandler.confirmQuit();
    }
}
