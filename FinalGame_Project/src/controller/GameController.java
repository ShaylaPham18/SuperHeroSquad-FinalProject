package controller;

import model.Player;
import model.Puzzle;
import model.Room;
import view.PuzzleView;
import view.Frame;

import java.awt.*;
import java.util.Map;
import java.util.Scanner;

public class GameController {
    private final Player player;
    private final Map<String, Room> rooms;
    private final Scanner scanner;

    public GameController(Player player, Map<String, Room> rooms) {
        this.player = player;
        this.rooms = rooms;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        Room current=rooms.get("1ew");//Justin-Come back here
        player.setCurrentRoom(current);
        System.out.println("🎮 Welcome to The Infected Hospital!");
        System.out.println("Type 'explore', 'go <direction>', 'solve', or 'quit'.");

        boolean running = true;
        while (running) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("help")){
                Frame frame=new Frame();
                frame.helpMenu();
            }

            if (input.equals("quit")) {
                System.out.println("👋 Thanks for playing!");
                running = false;

            } else if (input.equals("explore")) {
                Room room = player.getCurrentRoom();
                System.out.println("📍 " + room.getRoomName());
                System.out.println(room.getRoomDescription());

                if (room.getPuzzle() != null && !room.getPuzzle().isSolved()) {
                    System.out.println("🧩 There's a puzzle in this room. Try 'solve'.");
                }

                System.out.println("🚪 Exits: " + room.getExitDirections()); // assumes getExitDirections() exists

            } else if (input.startsWith("go ")) {
                String direction = input.substring(3).toUpperCase();
                Room next = player.getCurrentRoom().getExits(direction);
                if (next != null) {
                    player.setCurrentRoom(next);
                    System.out.println("➡️ You moved to: " + next.getRoomName());
                } else {
                    System.out.println("❌ You can't go that way.");
                }

            } else if (input.equals("solve")) {
                handlePuzzle();

            } else {
                System.out.println("❓ Unknown command. Try: explore, go <direction>, solve, quit.");
            }
        }
    }

    public void handlePuzzle() {
        Puzzle puzzle = player.getCurrentRoom().getPuzzle();

        if (puzzle == null) {
            System.out.println("❌ There is no puzzle in this room.");
        } else if (puzzle.isSolved()) {
            System.out.println("✅ You've already solved this puzzle.");
        } else {
            PuzzleController controller = new PuzzleController(puzzle, new PuzzleView());
            controller.startPuzzle();

            if (controller.isPuzzleSolved()) {
                System.out.println("🗝️ The puzzle seems to have unlocked something...");

            }
        }
    }
}
