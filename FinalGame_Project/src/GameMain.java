import controller.PuzzleController;
import loader.FileLoader;
import model.Floors;
import model.Puzzle;
import model.Room;
import view.PuzzleView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        try {
            // Load puzzles from file
            List<Puzzle> puzzles = FileLoader.loadPuzzles("src/puzzles.txt");
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
        }

       GameMain gameMain=new GameMain();

        FileLoader fileLoader=new FileLoader();
        fileLoader.readRooms();
    }

    private static void helpMenu(){
        Frame frame=new Frame();
        frame.helpMenu();
    }
}
