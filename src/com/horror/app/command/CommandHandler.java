package com.horror.app.command;

public class CommandHandler {
    public static String handleCommand(String userInput) {
        try {
            String[] commandArgs = userInput.split(" ");

            if (commandArgs.length == 1 || commandArgs.length == 2) {
                String action = commandArgs[0];
                ActionType actionType = ActionType.valueOf(action.toUpperCase());
                Command command = CommandFactory.getCommand(actionType);

                if (commandArgs.length == 2) {
                    String actionNoun = commandArgs[1];
                    return command.execute(actionNoun);
                } else {
                    return command.execute();
                }
            } else {
                return "Command should be in `go(verb) south(noun)` format, please type `help` to list commands";
            }
        } catch (IllegalArgumentException e) {
            return "Type `help` to list commands";
        }
    }
}

