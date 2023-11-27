package com.horror.app;

class HelpCommand implements Command{
    @Override
    public void execute(String noun) {
        System.out.println("Please enter command `help` without any argument");
    }

    @Override
    public void execute() {
        System.out.println("Commands:\n" +
                "- go [direction]\n" +
                "- look [item]\n" +
                "- quit\n"
        );
    }
}
