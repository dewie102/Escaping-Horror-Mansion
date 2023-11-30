package com.horror.app.command;

class HelpCommand implements Command{
    @Override
    public String execute(String noun) {
        return String.format("Please enter command `help` without any argument, Type `help` to list commands");
    }

    @Override
    public String execute() {
        return "Commands:\n" +
                "- go [direction]\n" +
                "- look [item]\n" +
                "- quit\n" +
                "- help\n";
    }
}
