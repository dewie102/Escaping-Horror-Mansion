package com.horror.app.command;

public enum ActionType {
    GO("go [direction]", "Please enter command with an argument, example: go [direction]. Type `help` to list commands"),
    LOOK("look [item]", ""),
    GET("get [item]", "Please enter command with an argument, example: get [item]. Type `help` to list commands"),
    DROP("drop [item]", "Please enter command with an argument, example: drop [item]. Type `help` to list commands"),
    USE("use [item]", "Please enter command with an argument, example: use [item]. Type `help` to list commands"),
    HELP("help", "Please enter command `help` without any argument."),
    SAVE("save", "Please enter command `save` without any argument, Type `help` to list commands"),
    QUIT("quit", "Please enter command `quit` without any argument, Type `help` to list commands");

    private final String displayString;
    private final String errorMessage;


    ActionType(String displayString, String errorMessage) {
        this.displayString = displayString;
        this.errorMessage = errorMessage;
    }

    public String getDisplayString() {
        return displayString;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}
