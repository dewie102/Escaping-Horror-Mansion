package com.horror.app;

class QuitCommand implements Command{
    @Override
    public void execute(String noun) {
        System.out.println("Please enter command `quit` without any argument");
    }

    @Override
    public void execute() {
        InputHandler.confirmQuit(Controller.scanner);
    }
}
