package com.horror.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.horror.Item;
import com.horror.Player;
import com.horror.Room;
import com.horror.Usable;
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
    private boolean foundSaveGame = true;
    private boolean continuing = false;
    private static final String SAVED_ROOMS_FILE_PATH = "resources/saved/savedRooms.json";
    private static final String SAVED_PLAYER_FILE_PATH = "resources/saved/savedPlayer.json";
    
    
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
        if(!continuing) {
            printStory();
            displayHandler.displayEnterToContinuePrompt();
        }
        
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
    private void handleMenuChoice(List<MenuOption> options) throws IOException {
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
                    loadNewGame(currentLevel);
                    if (foundSaveGame) {
                        Files.walk(Paths.get("resources/saved"))
                                .sorted(Comparator.reverseOrder())
                                .map(Path::toFile)
                                .forEach(File::delete);
                    }
                    break;
                case QUIT: // // first element in the options list
                    CommandHandler.handleCommand("quit");
                    if(isGameOver) {
                        exitGame();
                    }
                    break;
                case CONTINUE:
                    startPlaying = true;
                    loadSavedGame();
                    continuing = true;
                    break;
                default:
                    displayHandler.displayInvalidMenuOptionSelected();
            }
        }

        playGame();
    }

    public void execute() throws IOException {
// Load in resources and create objects needed to start the game
        initialize();

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
    
    private void loadNewGame(int level) {
        rooms = JsonTextLoader.loadLevelFromFile(String.format("/level_%s.json", level));
        for(Room room : rooms.values()) {
            room.linkHiddenItemsToFurniture();
        }
        player = new Player("George", rooms.get("bedroom"), new HashMap<>());
    }

    private void loadSavedGame() {
        rooms = JsonTextLoader.loadLevelFromFile(String.format("/saved/savedRooms.json", currentLevel));
        for(Room room : rooms.values()) {
            room.linkHiddenItemsToFurniture();
        }
        player = JsonTextLoader.loadPlayerFromFile("/saved/savedPlayer.json");
    }

    private void initialize() {
        foundSaveGame = lookForSavedGame();
        if (foundSaveGame) {
            loadSavedGame();
        } else {
            loadNewGame(currentLevel);
        }
        gameText = JsonTextLoader.loadHashMapFromFile("/story.json");
        Controller.displayHandler = JsonTextLoader.loadDisplayHandlerClass("/display_text.json");
    }
    
    private boolean lookForSavedGame() {

        return Files.exists(Paths.get(SAVED_ROOMS_FILE_PATH)) && Files.exists(Paths.get(SAVED_PLAYER_FILE_PATH));
    }

    public void saveGame() {
        JsonTextSaver.saveRoomMapToFile(rooms, SAVED_ROOMS_FILE_PATH);
        JsonTextSaver.savePlayerToFile(player, SAVED_PLAYER_FILE_PATH);
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

    public String useItem(String itemName) {
        Usable item= player.getInventory().getOrDefault(itemName, null);

        if (item != null) {
            switch (item.use()) {
                case MONSTER:
                    if (getCurrentRoom().getEnemyMap().containsKey("monster")) {
                        getCurrentRoom().getEnemyMap().remove("monster");
                        return "You successfully defeated the monster!";
                    } else {
                        return "No monster in the room!";
                    }
                case NULL:
                default:
                    return String.format("%s can not be used!", itemName);
            }
        } else {
            return String.format("%s does not exist in your inventory!", itemName);
        }

    }
}