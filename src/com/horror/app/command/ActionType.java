package com.horror.app.command;

public enum ActionType {
    GO("go [direction]"),
    LOOK("look [item]"),
    GET("get [item]"),
    DROP("drop [item]"),
    HELP("help"),
    QUIT("quit");

    private final String displayString;


    ActionType(String displayString) {
        this.displayString = displayString;
    }

    public String getDisplayString() {
        return displayString;
    }

}
