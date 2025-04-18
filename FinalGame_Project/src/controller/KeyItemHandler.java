package controller;

import model.Player;
import model.Puzzle;
import model.Room;
/**
 * Razan Abdalla
 */
public class KeyItemHandler {

    public static boolean handleKeyItemUse(String itemName, Player player) {
        Room currentRoom = player.getCurrentRoom();
        Puzzle puzzle = currentRoom.getPuzzle(); // may be null
        String roomName = currentRoom.getRoomName();

        // Use scalpel directly to solve the Security Room Puzzle
        if (itemName.equalsIgnoreCase("scalpel") && roomName.equalsIgnoreCase("Security Room")) {
            if (puzzle != null && puzzle.getName().equalsIgnoreCase("Security Room Puzzle") && !puzzle.isSolved()) {
                System.out.println("🔪 You used the scalpel to perform the retinal scan... Access granted!");
                puzzle.setSolved(true);
            } else {
                System.out.println("🔒 There's nothing to use the scalpel on here.");
            }
            return true;
        }


        // Use ID badge in Director’s Office or 3rd Floor Elevator
        if (itemName.equalsIgnoreCase("ID badge")) {
            if (puzzle != null && !puzzle.isSolved()) {
                if (roomName.equalsIgnoreCase("Director’s Office") && puzzle.getName().equalsIgnoreCase("Office Card Puzzle")) {
                    System.out.println("🪪 You swiped the ID badge. The office unlocks.");
                    puzzle.setSolved(true);
                    return true;
                } else if (roomName.equalsIgnoreCase("3rd Floor Elevator") && puzzle.getName().equalsIgnoreCase("Elevator Input Puzzle")) {
                    System.out.println("🪪 You swiped the ID badge. The elevator panel unlocks.");
                    puzzle.setSolved(true);
                    return true;
                }
            }
        }



        // Use Helicopter Key on Roof to win
        if (itemName.equalsIgnoreCase("Helicopter Key") && roomName.equalsIgnoreCase("Roof")) {
            System.out.println("🚁 You start the helicopter and escape!");
            System.out.println(" CONGRATULATIONS! YOU'VE BEATEN THE GAME!");
            System.exit(0);
        }

        return false; // not handled
    }
}
