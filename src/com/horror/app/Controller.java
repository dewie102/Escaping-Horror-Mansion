package com.horror.app;

import java.util.Map;

import com.horror.json.JsonTextLoader;

public class Controller {
    
    Map<String, String> gameText;

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void printBanner() {
        System.out.println("███████╗███████╗ ██████╗ █████╗ ██████╗ ██╗███╗   ██╗ ██████╗     ██╗  ██╗ ██████╗ ██████╗ ██████╗  ██████╗ ██████╗     ███╗   ███╗ █████╗ ███╗   ██╗███████╗██╗ ██████╗ ███╗   ██╗\n" +
                "██╔════╝██╔════╝██╔════╝██╔══██╗██╔══██╗██║████╗  ██║██╔════╝     ██║  ██║██╔═══██╗██╔══██╗██╔══██╗██╔═══██╗██╔══██╗    ████╗ ████║██╔══██╗████╗  ██║██╔════╝██║██╔═══██╗████╗  ██║\n" +
                "█████╗  ███████╗██║     ███████║██████╔╝██║██╔██╗ ██║██║  ███╗    ███████║██║   ██║██████╔╝██████╔╝██║   ██║██████╔╝    ██╔████╔██║███████║██╔██╗ ██║███████╗██║██║   ██║██╔██╗ ██║\n" +
                "██╔══╝  ╚════██║██║     ██╔══██║██╔═══╝ ██║██║╚██╗██║██║   ██║    ██╔══██║██║   ██║██╔══██╗██╔══██╗██║   ██║██╔══██╗    ██║╚██╔╝██║██╔══██║██║╚██╗██║╚════██║██║██║   ██║██║╚██╗██║\n" +
                "███████╗███████║╚██████╗██║  ██║██║     ██║██║ ╚████║╚██████╔╝    ██║  ██║╚██████╔╝██║  ██║██║  ██║╚██████╔╝██║  ██║    ██║ ╚═╝ ██║██║  ██║██║ ╚████║███████║██║╚██████╔╝██║ ╚████║\n" +
                "╚══════╝╚══════╝ ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═══╝ ╚═════╝     ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝    ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚══════╝╚═╝ ╚═════╝ ╚═╝  ╚═══╝\n");
    }

    private void printStory() {
        System.out.println(gameText.get("backstory"));
        System.out.println(gameText.get("introduction"));
        
        System.out.println("Please choose one of the options from below.\n1. Start a New Game\n2. Quit\n");
        System.out.print("Please enter your choice here: ");
    }

    private void playGame() {
        while (true) {
            System.out.print("> ");
            CommandHandler.handleCommand(InputHandler.checkInput());
        }
    }

    private void handleUserChoice() {
        while (true) {
            String selectedOption = InputHandler.checkInput();
            switch (selectedOption) {
                case "1":
                    clearScreen();
                    System.out.println("New Game Started, please enter a command!");
                    playGame();
                    break;
                case "2":
                    CommandHandler.handleCommand("quit");
                    mainGameWindow();
                default:
                    System.out.println("Please Enter a Valid Option.");
                    System.out.print("Please enter your choice here: ");
            }
        }
    }

    private void mainGameWindow() {
        clearScreen();
        printStory();
        handleUserChoice();
    }

    public void execute() {
        initialize();
        clearScreen();
        printBanner();

        while (true) {
            System.out.print("Press Enter to Continue: ");
            if (InputHandler.checkInput().equals("continue")) {
                mainGameWindow();
            } else {
                System.out.println("Invalid Option, please try again");
            }
        }
    }
    
    private void initialize() {
        gameText = JsonTextLoader.loadHashMapFromFile("resources/story.json");
    }
}