package controller;

import loader.FileLoader;
import model.Player;
import model.Puzzle;
import model.Room;
import view.PuzzleView;
import view.Frame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameController {
    private final Player player;
    private final Map<String, Room> rooms;
    private final Scanner scanner;
    KeyBoardShortCuts keyBoardShortCuts=new KeyBoardShortCuts();

    public GameController(Player player, Map<String, Room> rooms) {
        this.player = player;
        this.rooms = rooms;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        Room current=rooms.get("1ew");
        if (current==null){
            System.err.println("no room");
            return;
        }
        player.setCurrentRoom(current);
        System.out.println("🎮 Welcome to The Infected Hospital!");
        System.out.println("\nStaring room: "+current.toString()+" || available exits:"+current.getExitDirections());

        boolean running = true;
        while (running) {
            System.out.println("Type go<Direction> to navigate || or help to view all commands || or quit to end the game");
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit")) {
                System.out.println("👋 Thanks for playing!");
                running = false;

            } else if (input.equalsIgnoreCase("help")){
                Frame frame=new Frame();
                frame.helpMenu();
            } else if (input.equalsIgnoreCase("explore")||input.equalsIgnoreCase("ex")) {
                Room room = player.getCurrentRoom();
                System.out.println("📍 " + room.getRoomName());
                System.out.println(room.getRoomDescription());

                if (room.getPuzzle() != null && !room.getPuzzle().isSolved()) {
                    System.out.println("🧩 There's a puzzle in this room. Try 'solve'.");
                }

                System.out.println("🚪 Exits: " + room.getExitDirections()); // assumes getExitDirections() exists

            } else if (input.startsWith("go")) {
                String orginalDirection = input.substring(2).toUpperCase().trim();
                String direction= keyBoardShortCuts.resolveShortcut(orginalDirection);
                Room next = player.getCurrentRoom().getExits(direction);
                if (next != null) {
                    player.setCurrentRoom(next);
                    System.out.println("➡️ You moved to: " + next.getRoomName()+" || available exits: "+next.getExitDirections());
                } else {
                    System.out.println("❌ You can't go that way.");
                }

            } else if (input.equals("solve")) {
                handlePuzzle();

            } else {
                System.err.println("❓ Unknown command. Type go<Direction> to navigate || or help to view all commands || or quit to end the game");
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
