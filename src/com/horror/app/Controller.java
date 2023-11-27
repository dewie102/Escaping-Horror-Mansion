package com.horror.app;

import java.util.Map;
import java.util.Scanner;

import com.horror.Player;
import com.horror.Room;
import com.horror.json.JsonTextLoader;

public class Controller {
    // Only need one scanner object, making it public static to be accessed anywhere
    public static Scanner scanner = new Scanner(System.in);
    
    // All the game objects the controller keeps track of
    Map<String, String> gameText;
    Map<String, Room> rooms;
    Player player;
    
    // Variable to keep track if we are still playing or not
    boolean isPlaying = true;
    

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
    }
    
    private void printMenu() {
        System.out.println("Please choose one of the options from below.\n1. Start a New Game\n2. Help\n3. Quit");
        System.out.print("Please enter your choice here: ");
    }

    private void playGame() {
        clearScreen();
        printStory();
        
        while (isPlaying) {
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
                    CommandHandler.handleCommand("help");
                    System.out.print("Please enter your choice here: ");
                    handleUserChoice();
                    break;
                case "3":
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
        // Load in resources and create objects needed to start the game
        initialize();
        
        // Clear the screen and print the game title banner
        clearScreen();
        printBanner();
        // Prompt user to hit enter, doesn't matter what they type, just wait for the enter key
        System.out.print("Press Enter to Continue: ");
        scanner.nextLine();
        
        printMenu();
        handleUserChoice();
    }
    
    private void initialize() {
        gameText = JsonTextLoader.loadHashMapFromFile("resources/story.json");
        rooms = JsonTextLoader.loadLevelFromFile("resources/level_0.json");
        player = new Player("George", rooms.get("bedroom"), null);
    }
}