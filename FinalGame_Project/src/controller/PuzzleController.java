package controller;

import model.Player;
import model.Puzzle;
import model.Room;
import view.PuzzleView;

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
        // OPTIONAL: Suggest reading item from inventory if it's helpful
        String requiredItem = puzzle.getHintItem(); // You can create this field in Puzzle if needed
        if (requiredItem != null && !requiredItem.isBlank() && player.hasItem(requiredItem)) {
            System.out.println("📄 You notice you have something related to this puzzle in your inventory.");
            System.out.println("💡 Try reading or inspecting '" + requiredItem + "' for a clue.");
        }

        while (!puzzle.isSolved()) {
            view.displayPuzzlePrompt();
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
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

                default:
                    view.displayInvalidCommand();
            }
        }
        puzzleSolved = true;
        //✅ FR5.2 logic: remove puzzle from room if it's not Medicine Cabinet
        if (!puzzle.getName().equalsIgnoreCase("Medicine Cabinet Puzzle")) {
            room.setPuzzle(null);
            System.out.println("🧹The puzzle has been removed from the room.");
        }
    }

    private void handleSolve() {
        String requiredItem = puzzle.getHintItem();

        if (requiredItem != null && !requiredItem.isBlank()) {
            if (player.hasItem(requiredItem)) {
                System.out.println("✅ You used the " + requiredItem + " to solve the puzzle!");
                puzzle.setSolved(true);
                view.displayAttemptResult(true, puzzle);
                return;
            } else {
                System.out.println("❗ You need the item: '" + requiredItem + "' to solve this puzzle.");
                return;
            }
        }

        // If no item is required, allow user to enter an answer
        view.displayInputPrompt();
        String input = scanner.nextLine();
        boolean success = puzzle.attempt(input);
        view.displayAttemptResult(success, puzzle);
    }
    private void handleHint() {
        if (puzzle.canGetHint()) {
            view.displayHint(puzzle);
        } else {
            int remaining = Math.max(0, 3 - puzzle.getCurrentAttempts());
            System.out.println("❗ You need " + remaining + " more attempt(s) before you can get a hint.");
        }
    }

    public boolean isPuzzleSolved() {
        return puzzleSolved;
    }
}
