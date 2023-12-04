package com.horror.app;

import com.apps.util.Console;

import java.util.List;
import java.util.Map;

public class DisplayHandler {
    public static void clearScreen() {
        Console.clear();
    }

    // These variables are loaded in from json
    private String quitConfirm;
    private String quit;
    private String enterToContinue;
    private String prompt;
    private String commandNotRecognized;
    private Map<String, String> mainMenu;
    private Map<String, List<String>> gameTitle;

    // This is set in the program
    private String lastCommandOutput;

    public DisplayHandler() {
    }

    public void displayTextClearBefore(String text, boolean clearBefore) {
        if(clearBefore) {
            clearScreen();
        }
        System.out.println(text);
    }

    public void displayQuit() {
        System.out.println(quit);
    }

    public void displayBanner() {
        clearScreen();
        for(String line : gameTitle.get("line1")) {
            System.out.println(line);
        }
        for(String line : gameTitle.get("line2")) {
            System.out.println(line);
        }
        for(String line : gameTitle.get("line3")) {
            System.out.println(line);
        }
    }

    public void displayEnterToContinue() {
        System.out.println(enterToContinue);
        Controller.scanner.nextLine();
    }

    public void displayPrompt() {
        System.out.println(prompt);
    }

    public void displayLastCommandOutput() {
        System.out.println(lastCommandOutput);
    }

    public void displayCommandNotRecognized() {
        System.out.println(commandNotRecognized);
    }

    public void displayMainMenu() {

    }

    public void setLastCommandOutput(String output) {
        this.lastCommandOutput = output;
    }
}
