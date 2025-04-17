package controller;

import loader.FileLoader;
import model.Items;
import model.Player;
import model.Puzzle;
import model.Room;
import view.PuzzleView;
import view.Frame;

import java.util.Map;
import java.util.Scanner;

public class GameController {
    private final Player player;
    private final Map<String, Room> rooms;
    private final Scanner scanner;
    KeyBoardShortCuts keyBoardShortCuts = new KeyBoardShortCuts();

    public GameController(Player player, Map<String, Room> rooms) {
        this.player = player;
        this.rooms = rooms;
        this.scanner = new Scanner(System.in);
    }

    //Justin, Razan, Shayla, Nelly
    public void start() {
        Room current = rooms.get("1ew");
        if (current == null) {
            System.err.println("no room");
            return;
        }
        player.setCurrentRoom(current);
        System.out.println("🎮🎮🎮🎮🎮🎮🎮🎮 Welcome to The Infected Hospital! 🎮🎮🎮🎮🎮🎮🎮🎮");
        System.out.println("Type go<Direction> to navigate || or help to view all commands || or quit to end the game");
        System.out.println("\nStaring room: " + current.getRoomName() + " || available exits:" + current.getExitDirections());

        boolean running = true;
        while (running) {
            // 🏁 WIN CONDITION CHECK
            if (checkWinCondition(player)) {
                System.out.println("🎉 YOU WIN! You’ve collected all keys and reached the final room!");
                running = false;
                continue;
            }

            System.out.println("Type go<Direction> to navigate || or help to view all commands || or quit to end the game");
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit")) {
                System.out.println("👋 Thanks for playing!");
                running = false;

            } else if (input.equalsIgnoreCase("help")) {
                Frame frame = new Frame();
                frame.helpMenu();

            } else if (input.equalsIgnoreCase("explore") || input.equalsIgnoreCase("ex")) {
                Room room = player.getCurrentRoom();
                System.out.println("📍 " + room.getRoomName());
                System.out.println(room.getRoomDescription());

                if (room.getPuzzle() != null && !room.getPuzzle().isSolved()) {
                    System.out.println("👀 Something about this room seems off... Maybe try 'inspect'?");
                }

                if (!room.getRoomInventory().isEmpty()) {
                    System.out.println(" --> There seems to be stuff in this room. Perhaps you should inspect them.");
                }

                System.out.println("🚪 Exits: " + room.getExitDirections());

            } else if (input.startsWith("go")) {
                current.setRoomHasBeenVisited(true);
                String orginalDirection = input.substring(2).toUpperCase().trim();
                String direction = keyBoardShortCuts.resolveShortcut(orginalDirection);
                Room next = player.getCurrentRoom().getExits(direction);
                if (next != null) {
                    player.setCurrentRoom(next);

                    System.out.println("➡️ You moved to: " + next.getRoomName() + " || available exits: " + next.getExitDirections());

                    if (next.isRoomHasBeenVisited()) {
                        next.beenHereBefore();
                    }
                    next.setRoomHasBeenVisited(true);
                } else {
                    System.out.println("❌ You can't go that way.");
                }
            } else if (input.equals("solve")) {
                handlePuzzle();

            } else if (input.equals("inspect")) {
                handleInspect();

                //Shay, for pickup item
            } else if (input.startsWith("pickup")){
                String itemName = input.substring(6).trim();
                Room currentRoom = player.getCurrentRoom();
                Items itemToPickup = null;
                for (Items item : currentRoom.getRoomInventory()) {
                    if (item.getName().equalsIgnoreCase(itemName)) {
                        itemToPickup = item;
                        break;
                    }
                }
                //Checks if item is in room and able to be picked up
                if (itemToPickup != null){
                    currentRoom.getRoomInventory().remove(itemToPickup);
                    player.pickupItem(itemToPickup);
                    System.out.println("You picked up: " + itemToPickup.getName());
                } else {
                    System.out.println("❌ That item isn't in this room.");
                }

                //Shay, for consume item
            }else if(input.startsWith("consume")){
                String itemName = input.substring(6).trim();
                player.consumeItem(itemName);

            }else {
                System.err.println("❓ Unknown command. Type go<Direction> to navigate || or help to view all commands || or quit to end the game");
            }
        }
    }


            public void handlePuzzle () {
                Puzzle puzzle = player.getCurrentRoom().getPuzzle();

                if (puzzle == null) {
                    System.out.println("❌ There is no puzzle in this room.");
                } else if (puzzle.isSolved()) {
                    System.out.println("✅ You've already solved this puzzle.");
                } else {
                    PuzzleController controller = new PuzzleController(puzzle, new PuzzleView(), player.getCurrentRoom(), player);
                    controller.startPuzzle();

                    if (controller.isPuzzleSolved()) {
                        System.out.println("🗝️ The puzzle seems to have unlocked something...");
                    }
                }
            }


            // ✅ WIN CONDITION METHOD
            public boolean checkWinCondition (Player player){
                // Final room ID is assumed to be "3roof" – update if different
                boolean inFinalRoom = player.getCurrentRoom().getRoomID().equalsIgnoreCase("3roof");

                boolean hasAllKeys = player.hasItem("Keycard") &&
                        player.hasItem("ElevatorPass") &&
                        player.hasItem("MasterKey");

                return inFinalRoom && hasAllKeys;
            }

            public void handleInspect () {
                Room room = player.getCurrentRoom();
                Puzzle puzzle = room.getPuzzle();

                if (puzzle == null) {
                    System.out.println("🔍 You look around carefully, but there's nothing unusual to inspect here.");
                } else if (puzzle.isSolved()) {
                    System.out.println("✅ You recall solving the puzzle here already.");
                } else {
                    System.out.println("🧩 You uncover a hidden mechanism... It's a puzzle!");
                    System.out.println("📝 " + puzzle.getDescription());
                    System.out.println("Use the 'solve' command to attempt solving it.");
                }
            }
        }