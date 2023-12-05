package com.horror.app;

import java.util.*;

import com.horror.Player;
import com.horror.Room;
import com.horror.app.command.CommandHandler;
import com.horror.json.JsonTextLoader;
import com.horror.json.JsonTextSaver;

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

    private void printCharacterStatus() {
        displayHandler.displayTextClearBefore(getCurrentRoom().getFullDescription(), true);
        displayHandler.displayTextClearBefore("\n" + player.getInventoryDisplayString(), false);
    }

    private void playGame() {
        printStory();
        displayHandler.displayEnterToContinuePrompt();
        
        while (!isGameOver) {
            printCharacterStatus();

            displayHandler.displayLastCommandOutput();
            
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
            displayHandler.displayMainMenu(options);
            displayHandler.displayMenuChoicePrompt();
            String selectedOptionInput = scanner.nextLine();
            
            int selectedNumber;
            try{
                selectedNumber = Integer.parseInt(selectedOptionInput) - 1;
            } catch(NumberFormatException e) {
                displayHandler.displayInvalidMenuOptionSelected();
                continue;
            }
            
            if(selectedNumber < 0 || selectedNumber >= options.size()) {
                displayHandler.displayInvalidMenuOptionSelected();
                continue;
            }
            
            MenuOption selectedOption = options.get(selectedNumber);
            
            switch (selectedOption) {
                case NEWGAME: // first element in the options list
                    startPlaying = true;
                    break;
                case QUIT: // // first element in the options list
                    CommandHandler.handleCommand("quit");
                    if(isGameOver) {
                        exitGame();
                    }
                    break;
                case CONTINUE:
                    // TODO: change this for continue action/ prompt
                    System.out.println("do something");
                default:
                    displayHandler.displayInvalidMenuOptionSelected();
            }
        }

        playGame();
    }

    public void execute() {
// Load in resources and create objects needed to start the game
//        initialize();
        loadSavedGame();

        Controller.displayHandler.displayBanner();
        // Prompt user to hit enter, doesn't matter what they type, just wait for the enter key
        displayHandler.displayEnterToContinuePrompt();

        List<MenuOption> mainMenu = generateMainMenu();
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

    private void loadSavedGame() {
        gameText = JsonTextLoader.loadHashMapFromFile("/story.json");
        rooms = JsonTextLoader.loadLevelFromFile(String.format("/saved/savedRooms.json", currentLevel));
        for(Room room : rooms.values()) {
            room.linkHiddenItemsToFurniture();
        }
        player = JsonTextLoader.loadPlayerFromFile("/saved/savedPlayer.json");
        Controller.displayHandler = JsonTextLoader.loadDisplayHandlerClass("/display_text.json");
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

    public void saveGame() {
        JsonTextSaver.saveRoomMapToFile(rooms, "resources/saved/savedRooms.json");
        JsonTextSaver.savePlayerToFile(player, "resources/saved/savedPlayer.json");
    }
    
    public void exitGame() {
        displayHandler.displayQuit();
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