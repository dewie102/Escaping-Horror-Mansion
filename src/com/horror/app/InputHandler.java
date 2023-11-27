package com.horror.app;

import java.util.Scanner;

class InputHandler {

    public static String checkInput() {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        if (input.isEmpty()) {
            return "continue";
        } else {
            return input;
        }
    }

    public static void confirmQuit(Scanner scanner) {
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Are you sure you want to quit the game [y/n]? : ");
            String quitConfirm = scanner.nextLine().trim();

            if (quitConfirm.equalsIgnoreCase("y") || quitConfirm.equalsIgnoreCase("yes")) {
                System.out.println("Quitting.... Thanks for playing!");
                validInput = true;
                System.exit(0);
            } else if (quitConfirm.equalsIgnoreCase("n") || quitConfirm.equalsIgnoreCase("no")) {
                validInput = true;
            }
            else {
                System.out.println("Invalid response, please try again");
            }
        }
    }
}
