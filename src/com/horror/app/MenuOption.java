package com.horror.app;

public enum MenuOption {
    NEWGAME("newGame"),
    CONTINUE("continue"),
    QUIT("quit");

    // Display string should match dictionary key in DisplayHandler/ json
    private final String displayString;

    MenuOption(String displayString) {
        this.displayString = displayString;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
