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
                if (confirmSave()) {
                    Controller.getInstance().saveGame();
                    Controller.displayHandler.displayGameSaved();
                }
            } else if (quitConfirm.equalsIgnoreCase("n") || quitConfirm.equalsIgnoreCase("no")) {
                validInput = true;
            }
            else {
                Controller.displayHandler.displayInvalidMenuOptionSelected();
            }
        }
        
        return quit;
    }

    public static boolean confirmSave() {
        boolean validInput = false;
        boolean save = false;

        while (!validInput) {
            Controller.displayHandler.displayConfirmSaveGame();
            String saveConfirm = Controller.scanner.nextLine().trim();

            if (saveConfirm.equalsIgnoreCase("y") || saveConfirm.equalsIgnoreCase("yes")) {
                validInput = true;
                save = true;
            } else if (saveConfirm.equalsIgnoreCase("n") || saveConfirm.equalsIgnoreCase("no")) {
                validInput = true;
            }
            else {
                Controller.displayHandler.displayInvalidMenuOptionSelected();
            }
        }

        return save;
    }
}
