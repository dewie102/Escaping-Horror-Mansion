package com.horror.app.command;

import com.horror.Direction;
import com.horror.app.Controller;

import java.util.Arrays;
import java.util.List;

class GoCommand implements Command {
    @Override
    public void execute(String noun) {
        boolean isProperDirection =
                Arrays.stream(Direction.values()).anyMatch((t) -> t.name().equals(noun.toUpperCase()));
        
        Direction direction = Direction.valueOf(noun.toUpperCase());
        
        if(isProperDirection) {
            String nextRoomName = Controller.getInstance().getCurrentRoom().getNeighbors().getOrDefault(direction,
                    null);
            if(nextRoomName != null) {
                Controller.getInstance().getPlayer().goTo(Controller.getInstance().getRoomByName(nextRoomName));
            } else { // If the direction provided doesn't lead to a room
                System.out.printf("Sorry, you can't go %s\n", noun);
            }
        } else { // If the provided noun is not an allowed direction
            System.out.printf("Please choose one of the following directions: %s", String.join(",",
                    List.of(Arrays.toString(Direction.values()))));
        }
    }

    @Override
    public void execute() {
        System.out.println("Please enter command with an argument, example: go south. Type `help` to list commands");
    }
}
