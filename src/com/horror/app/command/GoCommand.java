package com.horror.app.command;

import com.horror.Direction;
import com.horror.Room;
import com.horror.app.Controller;
import com.horror.app.DisplayHandler;

import java.util.Arrays;
import java.util.List;

class GoCommand implements Command {
    @Override
    public String execute(String noun)  {
        boolean isProperDirection =
                Arrays.stream(Direction.values()).anyMatch((t) -> t.name().equals(noun.toUpperCase()));
        
        if(isProperDirection) {
            // Get the direction object based on the passed in value
            Direction direction = Direction.valueOf(noun.toUpperCase());
            // Get the room name, this will be null if the neighbor doesn't exist
            String nextRoomName = Controller.getInstance().getCurrentRoom().getNeighborMap().getOrDefault(direction,
                    null);
            
            // If the room name exists, move the player into that room
            if(nextRoomName != null) {
                Room nextRoom = Controller.getInstance().getRoomByName(nextRoomName);
                if(nextRoom.isLocked()) {
                    if(Controller.getInstance().getPlayer().getInventory().containsKey(nextRoom.getUnlockItem())) {
                        nextRoom.setLocked(false);
                        Controller.getInstance().getPlayer().removeItemFromInventory(nextRoom.getUnlockItem());
                    } else {
                        return String.format("Sorry, this door appears locked, please obtain '%s'",
                                nextRoom.getUnlockItem());
                    }
                }
                DisplayHandler.clearScreen();
                Controller.getInstance().getPlayer().goTo(nextRoom);
                return "";
            } else { // If the direction provided doesn't lead to a room
                return String.format("Sorry, you can't go %s\n", noun);
            }
        } else { // If the provided noun is not an allowed direction
            return String.format("Please choose one of the following directions: %s", String.join(",",
                    List.of(Arrays.toString(Direction.values()))));
        }
    }

    @Override
    public String execute() {
        return ActionType.GO.getErrorMessage();
    }
}
