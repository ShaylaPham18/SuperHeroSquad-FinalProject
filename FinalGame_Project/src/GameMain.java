import controller.PuzzleController;
import loader.FileLoader;
import model.Puzzle;
import view.PuzzleView;

import java.util.List;
import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        try {
            // Load rooms
            FileLoader fileLoader = new FileLoader();
            fileLoader.readRooms();  // Reads from "room.txt"

            // Load puzzles
            List<Puzzle> puzzles = FileLoader.loadPuzzles("puzzles.txt");
            System.out.println("🧩 Loaded " + puzzles.size() + " puzzles!");

            // List puzzle names
            System.out.println("\nAvailable puzzles:");
            for (int i = 0; i < puzzles.size(); i++) {
                System.out.println((i + 1) + ". " + puzzles.get(i).getName());
            }

            // Ask user to choose one
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nChoose a puzzle to try (enter number): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            if (choice < 1 || choice > puzzles.size()) {
                System.out.println("❌ Invalid choice.");
                return;
            }

            Puzzle selectedPuzzle = puzzles.get(choice - 1);
            PuzzleView view = new PuzzleView();
            PuzzleController controller = new PuzzleController(selectedPuzzle, view);

            // Start puzzle interaction
            controller.startPuzzle();

        } catch (Exception e) {
            System.out.println("❗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Optional help menu method
    private static void helpMenu() {
        Frame frame = new Frame();
        frame.helpMenu();
    }
}
