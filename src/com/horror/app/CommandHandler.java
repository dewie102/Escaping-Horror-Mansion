package com.horror.app;

class CommandHandler {
    public static void handleCommand(String userInput) {
        try {
            String[] commandArgs = userInput.split(" ");

            if (commandArgs.length == 1 || commandArgs.length == 2) {
                String action = commandArgs[0];
                ActionType actionType = ActionType.valueOf(action.toUpperCase());
                Command command = CommandFactory.getCommand(actionType);

                if (commandArgs.length == 2) {
                    String actionNoun = commandArgs[1];
                    command.execute(actionNoun);
                } else {
                    command.execute();
                }
            } else {
                System.out.println("Command should be in `go(verb) south(noun)` format, please try again!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Type `help` to list commands");
        }
    }
}

