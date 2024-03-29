package com.horror.app.command;


class HelpCommand implements Command{
    @Override
    public String execute(String noun) {
        return ActionType.HELP.getErrorMessage();
    }

    @Override
    public String execute() {
        StringBuilder output = new StringBuilder();

        output.append("Commands:\n");

        for(ActionType action : ActionType.values()) {
            output.append(String.format("\t- %s\n", action.getDisplayString()));
        }

        return output.toString();
    }
}
