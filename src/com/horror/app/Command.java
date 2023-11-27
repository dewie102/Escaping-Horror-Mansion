package com.horror.app;

public interface Command {
    void execute(String noun);

    default void execute() {
    }
}
