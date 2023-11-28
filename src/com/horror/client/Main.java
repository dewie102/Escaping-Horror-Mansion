package com.horror.client;

import com.horror.app.Controller;

class Main {
    
    public static void main(String[] args) {
        Controller gameController = Controller.getInstance();
        gameController.execute();
    }
}