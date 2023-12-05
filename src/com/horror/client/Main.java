package com.horror.client;

import com.horror.app.Controller;

import java.io.IOException;

class Main {
    
    public static void main(String[] args) throws IOException {
        Controller gameController = Controller.getInstance();
        gameController.execute();
    }
}