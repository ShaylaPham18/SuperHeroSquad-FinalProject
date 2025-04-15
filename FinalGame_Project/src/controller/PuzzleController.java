package controller;

import model.Puzzle;
import view.PuzzleView;
import java.util.Scanner;

    public class PuzzleController {
        private final Puzzle puzzle;
        private final PuzzleView view;
        private final Scanner scanner;

        public PuzzleController(Puzzle puzzle, PuzzleView view) {
            this.puzzle = puzzle;
            this.view = view;
            this.scanner = new Scanner(System.in);
        }

        public void startPuzzle() {
            if (puzzle.isSolved()) {
                view.displayAlreadySolved();
                return;
            }

            view.displayPuzzleIntro(puzzle);

            while (!puzzle.isSolved()) {
                view.displayPuzzlePrompt();
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "solve":
                        view.displayInputPrompt();
                        String input = scanner.nextLine();
                        boolean success = puzzle.attempt(input);
                        view.displayAttemptResult(success, puzzle);
                        break;
                    case "hint":
                        if (puzzle.canGetHint()) {
                            view.displayHint(puzzle);
                        } else {
                            System.out.println("❗ You need more attempts before you can get a hint.");
                        }
                        break;
                    case "leave":
                        view.displayExitMessage();
                        return;
                    default:
                        view.displayInvalidCommand();
                }
            }
        }
    }
