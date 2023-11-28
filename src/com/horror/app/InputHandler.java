package com.horror.app;

public class InputHandler {
    // We could maybe simplify this to check for y, yes and anything else is interpreted as no?
    public static boolean confirmQuit() {
        boolean validInput = false;
        boolean quit = false;
        
        while (!validInput) {
            System.out.print("Are you sure you want to quit the game [y/n]? : ");
            String quitConfirm = Controller.scanner.nextLine().trim();

            if (quitConfirm.equalsIgnoreCase("y") || quitConfirm.equalsIgnoreCase("yes")) {
                validInput = true;
                quit = true;
            } else if (quitConfirm.equalsIgnoreCase("n") || quitConfirm.equalsIgnoreCase("no")) {
                validInput = true;
            }
            else {
                System.out.println("Invalid response, please try again");
            }
        }
        
        return quit;
    }
}
