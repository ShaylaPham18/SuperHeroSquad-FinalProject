import controller.GameController;
import controller.GameSaver;
import controller.PuzzleController;
import loader.FileLoader;
import loader.MonsterLoader;
import model.Player;
import model.Puzzle;
import model.Room;
import model.Monster;

import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // 1. Load rooms
            FileLoader fileLoader = new FileLoader();
            Map<String, Room> rooms = fileLoader.readRooms("FinalGame_Project/room.txt");

            // 2. Load puzzles and assign to rooms
            List<Puzzle> puzzles = FileLoader.loadPuzzles("FinalGame_Project/puzzles.txt");
            PuzzleController.assignPuzzlesToRooms(puzzles, rooms);

            // 3. Load items into rooms
            FileLoader.loadItems("FinalGame_Project/items.txt", rooms);

            // 4. Load monsters
            List<Monster> monsters = MonsterLoader.loadMonsters("FinalGame_Project/monsters.txt");

            // 5. Show game start menu
            System.out.println("🎮 Welcome to The Infected Hospital!");
            System.out.print("Type 'new' to start a new game or 'load' to continue from a previous save: ");
            String choice = scanner.nextLine().trim().toLowerCase();

            Player player;

            if (choice.equals("load")) {
                player = GameSaver.loadGame("save.txt", rooms, monsters);
                if (player.getCurrentRoom() == null) {
                    System.out.println("⚠️ Save file was incomplete. Starting a new game instead.");
                    player = new Player("Razan");
                    player.setCurrentRoom(rooms.get("1ew"));
                }
            } else {
                System.out.print("👤 Enter your name: ");
                String name = scanner.nextLine().trim();
                player = new Player(name.isEmpty() ? "Razan" : name);
                player.setCurrentRoom(rooms.get("1ew")); // starting room
            }

            // 6. Start the game
            GameController gameController = new GameController(player, rooms, monsters);
            gameController.start();

        } catch (Exception e) {
            System.out.println("❗ Error: " + e.getMessage());
        }
    }
}
