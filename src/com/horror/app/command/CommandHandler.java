package com.horror.app.command;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    public static String handleCommand(String userInput) {

        // will get ActionType and length of the verb used in the command
        HashMap<ActionType, Integer> actionDetails = CommandSynonymHandler.getActionDetails(userInput);

        // if command verb found
        if (!actionDetails.isEmpty()) {
            for (Map.Entry<ActionType, Integer> entry : actionDetails.entrySet()) {
                ActionType actionType = entry.getKey();
                int lengthOfMatchedWord = entry.getValue();

                String itemName = userInput.substring(lengthOfMatchedWord).trim();

                Command command = CommandFactory.getCommand(actionType);

                //
                if (!itemName.equals("")) {
                    return command.execute(itemName);
                }
                return command.execute();
            }
        }

        return "Type `help` to list commands";

    }}