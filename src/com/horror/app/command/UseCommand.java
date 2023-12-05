package com.horror.app.command;

import com.horror.app.Controller;

class UseCommand implements Command{
    @Override
    public String execute(String noun) {
        return Controller.getInstance().useItem(noun.toLowerCase());

    }

    @Override
    public String execute() {
        return ActionType.USE.getErrorMessage();
    }
}
