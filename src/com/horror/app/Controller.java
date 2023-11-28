package com.horror.app;

import java.util.Map;
import java.util.Scanner;
import com.apps.util.Console;

import com.horror.Player;
import com.horror.Room;
import com.horror.app.command.CommandHandler;
import com.horror.json.JsonTextLoader;

public class Controller {
    // Only need one scanner object, making it public static to be accessed anywhere
    public static Scanner scanner = new Scanner(System.in);
    
    // All the game objects the controller keeps track of
    Map<String, String> gameText;
    Map<String, Room> rooms;
    Player player;
    
    // Variable to keep track if we are still playing or not
    boolean isGameOver = false;
    
    // Create a singleton that can be accessed anywhere
    private static Controller instance = null;
    private Controller() {
    }
    
    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        
        return instance;
    }
    
    public static void clearScreen() {
        Console.clear();
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
        // Refactor this to be more dynamic, either from file or some enum or fixed array
        System.out.println("Please choose one of the options from below.");
        System.out.println("1. Start a New Game");
        System.out.println("2. Help");
        System.out.println("3. Quit");
    }

    private void playGame() {
        clearScreen();
        printStory();
        System.out.print("Press enter to continue: ");
        scanner.nextLine();
        clearScreen();
        System.out.println();
        printCurrentRoomDescription();
        
        while (!isGameOver) {
            System.out.print("> ");
            String input = scanner.nextLine();
            CommandHandler.handleCommand(input);
        }
        
        exitGame();
    }

    // Maybe refactor this into a class?
    private void handleMenuChoice() {
        boolean startPlaying = false;
        while (!startPlaying) {
            System.out.print("Please enter your choice here: ");
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "1": // Play
                    startPlaying = true;
                    break;
                case "2": // Help
                    CommandHandler.handleCommand("help");
                    break;
                case "3": // Quit
                    CommandHandler.handleCommand("quit");
                    if(isGameOver) {
                        exitGame();
                    }
                    break;
                default:
                    System.out.println("Please Enter a Valid Option.");
            }
        }
    
        playGame();
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
        handleMenuChoice();
    }
    
    private void initialize() {
        gameText = JsonTextLoader.loadHashMapFromFile("/story.json");
        rooms = JsonTextLoader.loadLevelFromFile("/level_0.json");
        player = new Player("George", rooms.get("bedroom"), null);
    }
    
    public void exitGame() {
        System.out.println("Quitting.... Thanks for playing!");
        System.exit(0);
    }
    
    public void printCurrentRoomDescription() {
        System.out.println(player.getCurrentRoom().getFullDescription());
    }
    
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
    
    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }
    
    public Room getRoomByName(String name) {
        return rooms.getOrDefault(name, null);
    }
    
    public Player getPlayer() {
        return player;
    }
}