import controller.GameController;
import loader.FileLoader;
import model.Player;
import model.Puzzle;
import model.Room;

import java.util.Map;
import java.util.List;

public class GameMain {
    public static void main(String[] args) {
        try {
            // 1. Load rooms
            FileLoader fileLoader = new FileLoader();
            Map<String, Room> rooms = fileLoader.readRooms();
            // 2. Load puzzles and assign to rooms you guys use this 1 PLEASE JUST COMMENT AND UNCOMMENT
<<<<<<< HEAD
          //List<Puzzle> puzzles = FileLoader.loadPuzzles("puzzles.txt");


            //List<Puzzle> puzzles = FileLoader.loadPuzzles("puzzles.txt");
            List<Puzzle> puzzles = FileLoader.loadPuzzles("FinalGame_Project/puzzles.txt");

=======
            List<Puzzle> puzzles = FileLoader.loadPuzzles("puzzles.txt");
            //List<Puzzle> puzzles = FileLoader.loadPuzzles("FinalGame_Project/puzzles.txt");
>>>>>>> 1fb411e9e35af5d9a4a4f5b6167e19988237dda8
            for (Puzzle puzzle : puzzles) {
                Room room = rooms.get(puzzle.getRoomLocation());
                if (room != null) {
                    room.setPuzzle(puzzle);
                }
            }

            // 3. Create player
            Player player = new Player("Razan");
            // 4. Start the game loop with GameController
            GameController gameController = new GameController(player, rooms);
            gameController.start();
        } catch (Exception e) {
            System.out.println("❗ Error: " + e.getMessage());
        }
    }
}
