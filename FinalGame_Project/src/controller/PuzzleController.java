package controller;

import model.Player;
import model.Puzzle;
import model.Room;
import view.PuzzleView;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class PuzzleController {
    private final Puzzle puzzle;
    private final PuzzleView view;
    private final Scanner scanner;
    private boolean puzzleSolved;
    private final Room room;
    private final Player player;

    public PuzzleController(Puzzle puzzle, PuzzleView view, Room room, Player player) {
        this.puzzle = puzzle;
        this.view = view;
        this.room = room;
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
        String requiredItem = puzzle.getHintItem();
        if (requiredItem != null && !requiredItem.isBlank() && player.hasItem(requiredItem)) {
            System.out.println(" You notice you have something related to this puzzle in your inventory.");
            System.out.println(" Try reading or inspecting '" + requiredItem + "' for a clue.");
        }

        while (!puzzle.isSolved()) {
            System.out.println("\nCommand options: [solve / hint / leave / use <item> / inventory]");
            System.out.print("⤷ What would you like to do? ");
            String input = scanner.nextLine().trim().toLowerCase();

            String[] parts = input.split(" ", 2);
            String action = parts[0];

            switch (action) {
                case "solve":
                case "s":
                    handleSolve();
                    break;

                case "hint":
                case "h":
                    handleHint();
                    break;
                case "leave":
                case "l":
                    view.displayExitMessage();
                    return;
                case "inventory":
                case "inv":
                    player.showInventory();
                    return;
                case "use":
                case "u":
                    if (parts.length > 1) {
                        handleUseItem(parts[1]);
                    } else {
                        System.out.println("Please specify an item, like: use ID badge");
                    }
                    break;

                default:
                    System.out.println("Invalid command. Please type 'solve', 'hint', 'leave', or 'use <item>'.");
            }
        }
        puzzleSolved = true;
        // FR5.2 logic: remove puzzle from room is solved if it's not Medicine Cabinet
        if (!puzzle.getName().equalsIgnoreCase("Medicine Cabinet Puzzle")) {
            room.setPuzzle(null);
            System.out.println("The puzzle has been removed from the room.");
        }
    }

    private void handleSolve() {
        System.out.println("\n✏--> Enter your answer below:");
        System.out.print("-> ");
        String input = scanner.nextLine().trim();

        boolean success = puzzle.attempt(input);
        view.displayAttemptResult(success, puzzle);

        if (success) {
            System.out.println("You solved the puzzle: " + puzzle.getName());
            String result = puzzle.getResultWhenSolved();
            if (result != null && !result.isBlank()) {
                System.out.println("Result: " + result);
            }
            puzzle.setSolved(true);
        }
    }

    private void handleHint() {
        if (puzzle.canGetHint()) {
            view.displayHint(puzzle);
        } else {
            int remaining = Math.max(0, 3 - puzzle.getCurrentAttempts());
            System.out.println("You need " + remaining + " more attempt(s) before you can get a hint.");
        }
    }

    public boolean isPuzzleSolved() {
        return puzzleSolved;
    }

    private void handleUseItem(String itemName) {
        if (!player.hasItem(itemName)) {
            System.out.println("You don’t have '" + itemName + "' in your inventory.");
            return;
        }
        if (puzzle.getName().equalsIgnoreCase("Office Card Puzzle") &&
                itemName.equalsIgnoreCase("ID badge")) {

            System.out.println("🔓 You used the ID badge to unlock access. Puzzle solved!");
            puzzle.setSolved(true);
            view.displayAttemptResult(true, puzzle);
            room.setPuzzle(null); // Remove puzzle if needed
            return;
        }

        System.out.println("That item doesn’t help solve this puzzle.");
    }

    public static void handlePuzzle(Player player) {
        Puzzle puzzle = player.getCurrentRoom().getPuzzle();
        if (puzzle == null) {
            System.out.println("There is no puzzle in this room.");
        } else if (puzzle.isSolved()) {
            System.out.println("You've already solved this puzzle.");
        } else {
            PuzzleController controller = new PuzzleController(puzzle, new PuzzleView(), player.getCurrentRoom(), player);
            controller.startPuzzle();

            if (controller.isPuzzleSolved()) {
                System.out.println("The puzzle seems to have unlocked something...");
            }
        }
    }
    public static void assignPuzzlesToRooms(List<Puzzle> puzzles, Map<String, Room> rooms) {
        for (Puzzle puzzle : puzzles) {
            Room room = rooms.get(puzzle.getRoomLocation());
            if (room != null) {
                room.setPuzzle(puzzle);
            } else {
                System.err.println("️--> Room not found for puzzle: " + puzzle.getName());
            }
        }
    }
}
