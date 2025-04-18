package controller;

import model.Player;
import model.Puzzle;
import model.Room;
import view.PuzzleView;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Items;
/**
 * Razan Abdalla
 */
public class PuzzleController {
    private final Puzzle puzzle;
    private final PuzzleView view;
    private final Scanner scanner;
    private final Room room;
    private final Player player;
    private boolean puzzleSolved;
    private Map<String, Room> rooms;

    public PuzzleController(Puzzle puzzle, PuzzleView view, Room room, Player player, Map<String,Room> rooms) {
        this.puzzle = puzzle;
        this.view = view;
        this.room = room;
        this.rooms = rooms;
        this.player = player;
        this.scanner = new Scanner(System.in);
        this.puzzleSolved = puzzle.isSolved();
    }
    public void startPuzzle() {
        if (puzzle.isSolved()) {
            view.displayAlreadySolved();
            puzzleSolved = true;
            return;
        }
        view.displayPuzzleIntro(puzzle);
        // Optional hint based on inventory
        String hintItem = puzzle.getHintItem();
        if (hintItem != null && !hintItem.isBlank() && player.hasItem(hintItem)) {
            System.out.println("-->You might want to inspect or read your '" + hintItem + "' for a clue.");
        }
        while (!puzzle.isSolved()) {
            System.out.println("\nCommand options: [solve / hint / leave / use <item> / inventory]");
            System.out.print("⤷ What would you like to do? ");
            String input = scanner.nextLine().trim().toLowerCase();
            String[] parts = input.split(" ", 2);
            String action = parts[0];

            switch (action) {
                case "try":
                    view.displayPuzzleIntro(puzzle);
                    return;
                case "solve":
                case "s" :
                     handleSolve();
                     return;
                case "hint":
                case "h":
                    handleHint();
                    return;
                case "leave":
                case  "l":
                    view.displayExitMessage();
                    return;
                case "inventory":
                case  "inv":
                    player.showInventory();
                    return;
                case "use":
                case "u":
                {
                    if (parts.length > 1) {
                        handleUseItem(parts[1]);
                    } else {
                        System.out.println("⚠Please specify an item, like: use ID badge");
                    }
                }
                return;
                default:
                    System.out.println(" XXX-->Invalid command. Please type 'solve', 'hint', 'leave', or 'use <item>'.");
            }
        }
        puzzleSolved = true;
    }

    private void handleSolve() {
        if (puzzle.getCorrectAnswerParts().length == 2 && !puzzle.isSolved()) {
            if (!puzzle.isFirstPartEntered()) {
                System.out.println("Enter the first code (or type 'exit' to leave):");
            } else {
                System.out.println("Enter the second code (or type 'exit' to leave):");
            }
        }

        while (!puzzle.isSolved()) {
            System.out.println("Enter your answer use 'exit' to exit solving mode or 'hint' for Hint");
            System.out.print("-> " );
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("🛑 Exiting solve mode.");
                return;
            }

            if (input.equals("hint")) {
                handleHint();
                continue;
            }

            boolean correct = puzzle.attempt(input);
            view.displayAttemptResult(correct, puzzle);

            if (correct) {
                System.out.println("You solved the puzzle: " + puzzle.getName());
                System.out.println("🏁 Result: " + puzzle.getResultWhenSolved());
                applyPuzzleResult(puzzle, player, rooms);
            } else {
                System.out.println("(0) --> Attempts so far: " + puzzle.getCurrentAttempts());
            }
        }

    }

    private void handleHint() {
        if (puzzle.canGetHint()) {
            view.displayHint(puzzle);
        } else {
            int attemptsLeft = Math.max(0, 3 - puzzle.getCurrentAttempts());
            System.out.println("🧩 Try " + attemptsLeft + " more time(s) before a hint becomes available.");
        }
    }

    private void handleUseItem(String itemName) {
        if (!player.hasItem(itemName)) {
            System.out.println("You don’t have '" + itemName + "' in your inventory.");
            return;
        }

        if (puzzle.getRequiredItem() != null && itemName.equalsIgnoreCase(puzzle.getRequiredItem())) {
            System.out.println("🔓 You used " + itemName + " to solve the puzzle!");
            puzzle.setSolved(true);
            view.displayAttemptResult(true, puzzle);
            room.setPuzzle(null);
            applyPuzzleResult(puzzle, player, rooms); // ✅ this applies the result
            puzzleSolved = true;
        } else {
            System.out.println("That item doesn’t help solve this puzzle.");
        }
    }




    public static void handlePuzzle(Player player, Map<String, Room> rooms) {
        Puzzle puzzle = player.getCurrentRoom().getPuzzle();

        if (puzzle == null) {
            System.out.println("🚫 There is no puzzle in this room.");
        } else if (puzzle.isSolved()) {
            System.out.println("✅ You've already solved this puzzle.");
        } else {
            PuzzleController controller = new PuzzleController(puzzle, new PuzzleView(), player.getCurrentRoom(), player, rooms);
            controller.startPuzzle();

            if (controller.isPuzzleSolved()) {
                System.out.println("🔓 The puzzle seems to have unlocked something...");
            }
        }
    }


    public static void assignPuzzlesToRooms(List<Puzzle> puzzles, Map<String, Room> rooms) {
        for (Puzzle puzzle : puzzles) {
            Room room = rooms.get(puzzle.getRoomLocation());
            if (room != null) {
                room.setPuzzle(puzzle);
            } else {
                System.err.println("⚠️ Room not found for puzzle: " + puzzle.getName());
            }
        }
    }

    public boolean isPuzzleSolved() {
        return puzzleSolved;
    }
    public static void applyPuzzleResult(Puzzle puzzle, Player player, Map<String, Room> rooms) {
        String result = puzzle.getResultWhenSolved();
        if (result == null || result.isEmpty()) return;

        Room currentRoom = player.getCurrentRoom(); // Used for item location, if needed

        // 🎁 If the result is "Gain [ItemName]", create and add item to inventory
        if (result.startsWith("Gain [")) {
            String itemName = result.substring(result.indexOf("[") + 1, result.indexOf("]"));
            Items newItem = new Items(
                    999,                             // Arbitrary ID
                    itemName,
                    "special",                      // Custom item type
                    0,                              // No stat value
                    "Gained by solving a puzzle.",
                    currentRoom.getRoomID(),
                    1
            );
            player.takeItem(newItem); // Use your own method
            System.out.println("🎁 You received: " + itemName);
        }

        // 🔓 If the result is "Unlocks [Room Name]", unlock that room
        else if (result.startsWith("Unlocks")) {
            String targetRoomName = result.replace("Unlocks", "").trim();
            for (Room room : rooms.values()) {
                if (room.getRoomName().equalsIgnoreCase(targetRoomName)) {
                    room.setLocked(false); // Unlock the room
                    room.setRequiredItem(null); // Remove item lock
                    System.out.println("🔓 " + targetRoomName + " is now unlocked.");
                    break;
                }
            }
        }

        // 🧭 If the result gives access to basement
        else if (result.contains("access to basement")) {
            Room elevator = rooms.get("ELE1"); // Your 1st floor elevator ID
            if (elevator != null) {
                elevator.setLocked(false);
                elevator.setRequiredItem(null);
                System.out.println("⬇️ Basement access enabled.");
            }
        }

        // 🧠 Story/environment changes
        else if (result.contains("Turns computers back on")) {
            System.out.println("🖥️ Computers in the Staff Lounge and Surveillance Room are now powered.");
        }

        else if (result.contains("Turns power back on")) {
            System.out.println("⚡ Power restored to Elevator and Electric Panel Puzzle.");
        }
    }

}
