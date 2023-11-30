package com.horror.app.command;

public interface Command {
    String execute(String noun);

    default String execute() {
        return "";
    }
}
