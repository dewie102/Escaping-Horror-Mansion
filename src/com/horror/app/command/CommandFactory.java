package com.horror.app.command;

import java.util.HashMap;

class CommandFactory {

    private static final HashMap<ActionType, Command> commandStorage = new HashMap<>();

    public static Command getCommand(ActionType actionType) {
        if (commandStorage.containsKey(actionType)) {
            return commandStorage.get(actionType);
        } else {
            switch(actionType) {
                case GO:
                    commandStorage.put(ActionType.GO, new GoCommand());
                    return commandStorage.get(ActionType.GO);
                case LOOK:
                    commandStorage.put(ActionType.LOOK, new LookCommand());
                    return commandStorage.get(ActionType.LOOK);
                case HELP:
                    commandStorage.put(ActionType.HELP, new HelpCommand());
                    return commandStorage.get(ActionType.HELP);
                case QUIT:
                    commandStorage.put(ActionType.QUIT, new QuitCommand());
                    return commandStorage.get(ActionType.QUIT);
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}

