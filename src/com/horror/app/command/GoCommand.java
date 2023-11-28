package com.horror.app.command;

import com.horror.Direction;
import com.horror.app.Controller;

import java.util.Arrays;
import java.util.List;

class GoCommand implements Command {
    @Override
    public void execute(String noun)  {
        boolean isProperDirection =
                Arrays.stream(Direction.values()).anyMatch((t) -> t.name().equals(noun.toUpperCase()));
        
        if(isProperDirection) {
            // Get the direction object based on the passed in value
            Direction direction = Direction.valueOf(noun.toUpperCase());
            // Get the room name, this will be null if the neighbor doesn't exist
            String nextRoomName = Controller.getInstance().getCurrentRoom().getNeighbors().getOrDefault(direction,
                    null);
            
            // If the room name exists, move the player into that room
            if(nextRoomName != null) {
                Controller.clearScreen();
                Controller.getInstance().getPlayer().goTo(Controller.getInstance().getRoomByName(nextRoomName));
                Controller.getInstance().printCurrentRoomDescription();
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
