package com.horror.app.command;

public interface Command {
    void execute(String noun);

    default void execute() {
    }
}
