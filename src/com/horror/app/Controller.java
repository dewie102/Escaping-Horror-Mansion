package com.horror.app;

import java.util.*;

import com.horror.Player;
import com.horror.Room;
import com.horror.app.command.CommandHandler;
import com.horror.json.JsonTextLoader;

public class Controller {
    // Only need one scanner object, making it public static to be accessed anywhere
    public static Scanner scanner = new Scanner(System.in);

    // Static displayHandler to be used whenever something needs to be displayed
    public static DisplayHandler displayHandler;
    
    // All the game objects the controller keeps track of
    private Map<String, String> gameText;
    private Map<String, Room> rooms;
    private Player player;
    private int currentLevel = 0;
    private boolean foundSaveGame = false;
    
    
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

    private void printStory() {
        displayHandler.displayTextClearBefore(gameText.get("backstory"), true);
        displayHandler.displayTextClearBefore(gameText.get("introduction"), false);
    }
    
    /*private void printMenu() {
        // Refactor this to be more dynamic, either from file or some enum or fixed array
        System.out.println("Please choose one of the options from below.");
        System.out.println("1. Start a New Game");
        System.out.println("2. Quit");
    }*/
    
    private void printCharacterStatus() {
        displayHandler.displayTextClearBefore(getCurrentRoom().getFullDescription(), true);
        displayHandler.displayTextClearBefore("\n" + player.getInventoryDisplayString() + "\n", false);
    }

    private void playGame() {
        printStory();
        displayHandler.displayEnterToContinue();
        
        while (!isGameOver) {
            printCharacterStatus();

            displayHandler.displayLastCommandOutput();
            System.out.println();
            
            displayHandler.displayPrompt();
            String input = scanner.nextLine();
            CommandHandler.handleCommand(input);
        }
        
        exitGame();
    }

    // Maybe refactor this into a class?
    private void handleMenuChoice(List<MenuOption> options) {
        boolean startPlaying = false;
        while (!startPlaying) {
            System.out.print("Please enter your choice here: ");
            String selectedOption = scanner.nextLine();
            switch (selectedOption) {
                case "1": // Play
                    startPlaying = true;
                    break;
                case "2": // Quit
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

        Controller.displayHandler.displayBanner();
        // Prompt user to hit enter, doesn't matter what they type, just wait for the enter key
        displayHandler.displayEnterToContinue();

        List<MenuOption> mainMenu = generateMainMenu();
        displayHandler.displayMainMenu(mainMenu);
        handleMenuChoice(mainMenu);
    }

    private List<MenuOption> generateMainMenu() {
        List<MenuOption> mainMenu = new ArrayList<>();

        mainMenu.add(MenuOption.NEWGAME);
        if(foundSaveGame) {
            mainMenu.add(MenuOption.CONTINUE);
        }
        mainMenu.add(MenuOption.QUIT);

        return mainMenu;
    }
    
    private void initialize() {
        gameText = JsonTextLoader.loadHashMapFromFile("/story.json");
        loadLevel(currentLevel);
        player = new Player("George", rooms.get("bedroom"), new HashMap<>());

        Controller.displayHandler = JsonTextLoader.loadDisplayHandlerClass("/display_text.json");
    }
    
    private void loadLevel(int level) {
        rooms = JsonTextLoader.loadLevelFromFile(String.format("/level_%s.json", level));
        for(Room room : rooms.values()) {
            room.linkHiddenItemsToFurniture();
        }
    }
    
    public void exitGame() {
        System.out.println("Quitting.... Thanks for playing!");
        System.exit(0);
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