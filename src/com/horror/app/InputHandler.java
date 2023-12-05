package com.horror.app;

public class InputHandler {
    // We could maybe simplify this to check for y, yes and anything else is interpreted as no?
    public static boolean confirmQuit() {
        boolean validInput = false;
        boolean quit = false;
        
        while (!validInput) {
            Controller.displayHandler.displayConfirmQuitPrompt();
            String quitConfirm = Controller.scanner.nextLine().trim();

            if (quitConfirm.equalsIgnoreCase("y") || quitConfirm.equalsIgnoreCase("yes")) {
                validInput = true;
                quit = true;
            } else if (quitConfirm.equalsIgnoreCase("n") || quitConfirm.equalsIgnoreCase("no")) {
                validInput = true;
            }
            else {
                Controller.displayHandler.displayInvalidMenuOptionSelected();
            }
        }
        
        return quit;
    }
}
