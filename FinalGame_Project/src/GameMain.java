import controller.GameController;
import controller.PuzzleController;
import loader.FileLoader;
import model.Player;
import model.Puzzle;
import model.Room;
import java.util.Map;
import java.util.List;

public class GameMain {
    public static void main(String[] args) {//justin razan shayla jose
        try {
            // 1. Load rooms
            FileLoader fileLoader = new FileLoader();
            Map<String, Room> rooms = fileLoader.readRooms();

            // 2. Load puzzles and assign to rooms you guys use this 1 PLEASE JUST COMMENT AND UNCOMMENT
            List<Puzzle> puzzles = FileLoader.loadPuzzles("puzzles.txt");
            //List<Puzzle> puzzles = FileLoader.loadPuzzles("FinalGame_Project/puzzles.txt");
            //  links each puzzles to the rooms --> Razan
            PuzzleController.assignPuzzlesToRooms(puzzles, rooms);
            //Shayla
           FileLoader.loadItems("items.txt", rooms);
           // FileLoader.loadItems("FinalGame_Project/items.txt",rooms);
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
