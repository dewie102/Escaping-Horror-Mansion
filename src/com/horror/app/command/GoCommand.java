package com.horror.app.command;

class GoCommand implements Command {
    @Override
    public void execute(String noun) {
        System.out.println("Going to " + noun);
    }

    @Override
    public void execute() {
        System.out.println("Please enter command with an argument, example: go south. Type `help` to list commands");
    }
}
