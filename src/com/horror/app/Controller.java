package com.horror.app;

import java.util.Scanner;

public class Controller {

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private String checkInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            return "continue";
        } else if (input.equalsIgnoreCase("quit")) {
            return "quit";
        } else {
            return input;
        }
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
        System.out.println("\nYou are caught in a storm on your way home from work in a small town, you can't see the road in the bad weather.\n" +
                "Desperate for shelter until the weather clears, you spot a house. Heading to the door, you notice it's abandoned. Entering the house, you find it dark, so you use your phone torch.\n" +
                "Tired from work, you find a bedroom with a broken bed frame. You put your jacket on the broken bed and lay down, and eventually fall asleep.\n" + "\n" +
                "You are woken up by a spooky noise, you look around, but there's no one.\n" +
                "The strange sound seems to come from outside. Using your phone's light, a shadow shows up, making the abandoned house feel even creepier.\n" +
                "As you try to figure things out, a cold breeze suddenly sweeps through the room, making you feel more uneasy, like the old house has secrets.\n" +
                "Now, you need to find a way out of this scary place.\n"
        );

        System.out.println("Please choose one of the options from below.\n1. Start a New Game\n2. Quit\n");
        System.out.print("Please enter your choice here: ");
    }

    private void playGame() {
        while (true) {
            System.out.print("> ");
            CommandHandler.handleInput(checkInput());
        }
    }

    private void handleUserChoice() {
        while (true) {
            String selectedOption = checkInput();
            switch (selectedOption) {
                case "1":
                    clearScreen();
                    System.out.println("New Game Started, please enter a command!");
                    playGame();
                    break;
                case "2":
                    System.out.println("Quitting the game...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please Enter a Valid Option.");
                    System.out.print("Please enter your choice here: ");
            }
        }
    }

    public void execute() {
        clearScreen();
        printBanner();

        while (true) {
            System.out.print("Press Enter to Continue: ");
            if (checkInput().equals("continue")) {
                clearScreen();
                printStory();
                handleUserChoice();

            } else {
                System.out.println("Invalid Option, please try again");
            }
        }
    }
}