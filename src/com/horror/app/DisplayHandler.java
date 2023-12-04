package com.horror.app;

import java.util.List;
import java.util.Map;

public class DisplayHandler {
    private String quitConfirm;
    private String quit;
    private String enterToContinue;
    private String prompt;
    private Map<String, String> mainMenu;
    private Map<String, List<String>> gameTitle;

    private static DisplayHandler instance;

    public static DisplayHandler getInstance() {
        if(instance == null) {
            instance = new DisplayHandler();
        }

        return instance;
    }

    private DisplayHandler() {

    }


    public void DisplayQuit() {

    }


}
