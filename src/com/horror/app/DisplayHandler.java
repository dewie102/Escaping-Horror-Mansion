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
    private String lastCommandOutput = "";

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

        for(String line : gameTitle.getOrDefault("line1", List.of("gameTitle:line1"))) {
            System.out.println(line);
        }
        for(String line : gameTitle.getOrDefault("line2", List.of("gameTitle:line1"))) {
            System.out.println(line);
        }
        for(String line : gameTitle.getOrDefault("line3", List.of("gameTitle:line1"))) {
            System.out.println(line);
        }
    }

    public void displayEnterToContinue() {
        System.out.print(enterToContinue);
        Controller.scanner.nextLine();
        clearScreen();
    }

    public void displayPrompt() {
        System.out.print(prompt);
    }

    public void displayLastCommandOutput() {
        System.out.println(lastCommandOutput);
    }

    public void displayCommandNotRecognized() {
        System.out.println(commandNotRecognized);
    }

    public void displayMainMenu(List<MenuOption> options) {
        System.out.println(mainMenu.getOrDefault("text", "mainMenu:text"));
        
        int optionNumber = 1;

        for(MenuOption option : options) {
            System.out.printf("%s. %s\n", optionNumber, option.toString());
            optionNumber++;
        }
    }

    public void setLastCommandOutput(String output) {
        this.lastCommandOutput = output;
    }
}
