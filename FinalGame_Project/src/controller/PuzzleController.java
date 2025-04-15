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


    public PuzzleController(Puzzle puzzle, PuzzleView view) {
        this.puzzle = puzzle;
        this.view = view;
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

        while (!puzzle.isSolved()) {
            view.displayPuzzlePrompt();
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "solve":
                    handleSolve();
                    break;

                case "hint":
                    handleHint();
                    break;

                case "leave":
                    view.displayExitMessage();
                    return;

                default:
                    view.displayInvalidCommand();
            }
        }
        puzzleSolved = true;
    }

    private void handleSolve() {
        view.displayInputPrompt();
        String input = scanner.nextLine();
        boolean success = puzzle.attempt(input);
        view.displayAttemptResult(success, puzzle);
    }
    private void handleHint() {
        if (puzzle.canGetHint()) {
            view.displayHint(puzzle);
        } else {
            System.out.println("❗ You need more attempts before you can get a hint.");
        }
    }
    public boolean isPuzzleSolved() {
        return puzzleSolved;
    }
}
