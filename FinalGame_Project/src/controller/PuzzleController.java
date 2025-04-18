package controller;

import model.Player;
import model.Puzzle;
import model.Room;
import view.PuzzleView;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
        // Optional hint based on inventory
        String hintItem = puzzle.getHintItem();
        if (hintItem != null && !hintItem.isBlank() && player.hasItem(hintItem)) {
            System.out.println("🧠 You might want to inspect or read your '" + hintItem + "' for a clue.");
        }

        while (!puzzle.isSolved()) {
            System.out.println("\nCommand options: [solve / hint / leave / use <item> / inventory]");
            System.out.print("⤷ What would you like to do? ");
            String input = scanner.nextLine().trim().toLowerCase();
            String[] parts = input.split(" ", 2);
            String action = parts[0];

            switch (action) {
                case "solve", "s" -> handleSolve();
                case "hint", "h" -> handleHint();
                case "leave", "l" -> {
                    view.displayExitMessage();
                    return;
                }
                case "inventory", "inv" -> player.showInventory();
                case "use", "u" -> {
                    if (parts.length > 1) {
                        handleUseItem(parts[1]);
                    } else {
                        System.out.println("⚠️ Please specify an item, like: use ID badge");
                    }
                }
                default -> System.out.println("❌ Invalid command. Please type 'solve', 'hint', 'leave', or 'use <item>'.");
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
            System.out.println("Enter your answer use 'exit' to exit solving mode'");
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
            puzzleSolved = true;
        } else {
            System.out.println("That item doesn’t help solve this puzzle.");
        }
    }



    public static void handlePuzzle(Player player) {
        Puzzle puzzle = player.getCurrentRoom().getPuzzle();

        if (puzzle == null) {
            System.out.println("🚫 There is no puzzle in this room.");
        } else if (puzzle.isSolved()) {
            System.out.println("✅ You've already solved this puzzle.");
        } else {
            PuzzleController controller = new PuzzleController(puzzle, new PuzzleView(), player.getCurrentRoom(), player);
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
}
