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
            System.out.println("📂 Working directory: " + System.getProperty("user.dir"));

            // 1. Load rooms
            FileLoader fileLoader = new FileLoader();
            Map<String, Room> rooms = fileLoader.readRooms();
            System.out.println("🏥 Loaded " + rooms.size() + " rooms!");

            // 2. Load puzzles and assign to rooms
            List<Puzzle> puzzles = FileLoader.loadPuzzles("puzzles.txt");
            for (Puzzle puzzle : puzzles) {
                Room room = rooms.get(puzzle.getRoomLocation());
                if (room != null) {
                    room.setPuzzle(puzzle);
                }
            }

            // 3. Create player and set starting room
            Player player = new Player("Razan");
            Room startingRoom = rooms.get("Pediatrics"); // Make sure this room exists
            if (startingRoom == null) {
                System.out.println("❌ Starting room not found.");
                return;
            }
            player.setCurrentRoom(startingRoom);

            // 4. Start the game loop with GameController
            GameController gameController = new GameController(player, rooms);
            gameController.start(); // ✅ This should launch the game loop

        } catch (Exception e) {
            System.out.println("❗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
