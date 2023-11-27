package com.horror.app;

class LookCommand implements Command{
    @Override
    public void execute(String noun) {
        System.out.println("Looking at " + noun);
    }

    @Override
    public void execute() {
        System.out.println("Please enter command with an argument, example: look chair. Type `help` to list commands");
    }
}
