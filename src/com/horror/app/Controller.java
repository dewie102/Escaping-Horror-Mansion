package com.horror.app;

import java.util.Map;
import java.util.Scanner;

import com.horror.Player;
import com.horror.Room;
import com.horror.app.command.CommandHandler;
import com.horror.json.JsonTextLoader;

public class Controller {
    // Only need one scanner object, making it public static to be accessed anywhere
    public static Scanner scanner = new Scanner(System.in);
    
    // Create a singleton that can be accessed anywhere
    private static Controller instance = null;
    
    // All the game objects the controller keeps track of
    Map<String, String> gameText;
    Map<String, Room> rooms;
    Player player;
    
    // Variable to keep track if we are still playing or not
    boolean isGameOver = false;
    
    private Controller() {
    }
    
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
        
        while (!isGameOver) {
            System.out.print("> ");
            String input = scanner.nextLine();
            CommandHandler.handleCommand(input);
        }
        
        exitGame();
    }

    // Maybe refactor this into a class?
    private void handleUserChoice() {
        boolean isValidInput = false;
        while (!isValidInput) {
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "1":
                    isValidInput = true;
                    clearScreen();
                    System.out.println("New Game Started, please enter a command!");
                    playGame();
                    break;
                case "2":
                    isValidInput = true;
                    CommandHandler.handleCommand("help");
                    System.out.print("Please enter your choice here: ");
                    handleUserChoice();
                    break;
                case "3":
                    isValidInput = true;
                    CommandHandler.handleCommand("quit");
                    mainGameWindow();
                    break;
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
    
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
    
    private void initialize() {
        gameText = JsonTextLoader.loadHashMapFromFile("resources/story.json");
        rooms = JsonTextLoader.loadLevelFromFile("resources/level_0.json");
        player = new Player("George", rooms.get("bedroom"), null);
    }
    
    public void exitGame() {
        System.out.println("Quitting.... Thanks for playing!");
        System.exit(0);
    }
    
    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        
        return instance;
    }
}