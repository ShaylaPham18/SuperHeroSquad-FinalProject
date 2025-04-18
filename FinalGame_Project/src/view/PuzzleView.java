package view;

import model.Puzzle;

// Iam adding this class for puzzle view we may add it to game view after we finish the work
public class PuzzleView {
    public void displayPuzzleIntro(Puzzle puzzle) {
            System.out.println("🧩 You found a strange puzzle: " + puzzle.getName());
            System.out.println("🧠 " + puzzle.getDescription());
            System.out.println("🔑 Type 'solve' to begin solving, or 'hint' to get a clue.");
        }

    public void displayAttemptResult(boolean success, Puzzle puzzle) {
        if (success) {
            System.out.println("✅ Puzzle solved! " + puzzle.getResultWhenSolved());
        } else {
            System.out.println("❌ Incorrect. Try again.");
        }
    }
    public void displayHint(Puzzle puzzle) {
        System.out.println("🔍 Hint: " + puzzle.getHint());
    }

    public void displayAlreadySolved() {
        System.out.println("You already solved this puzzle!");
    }


    public void displayExitMessage() {
        System.out.println("You’ve left the puzzle.");
    }
}

