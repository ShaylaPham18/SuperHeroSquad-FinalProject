package loader;

import model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In this file we should add all the loader methods of the files to read the files
 */
public class FileLoader {

    public static List<Puzzle> loadPuzzles(String filePath) throws IOException {
        List<Puzzle> puzzles = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 8) {
                String name = parts[0].trim();
                String description = parts[1].trim();
                String roomLocation = parts[2].trim();
                String correctAnswer = parts[3].trim();
                String resultWhenSolved = parts[4].trim();
                int maxAttempts = Integer.parseInt(parts[5].trim());
                String hint = parts[6].trim();
                String requiredItem = parts[7].trim();

                Puzzle puzzle = new Puzzle(name, description, roomLocation, correctAnswer, resultWhenSolved, maxAttempts, hint);
                puzzle.setRequiredItem(requiredItem);
                puzzles.add(puzzle);
            } else {
                System.err.println("⚠️ Invalid puzzle format: " + line);
            }
        }

        reader.close();
        return puzzles;
    }

    // original method for teammates using "room.txt"
    public Map<String, Room> readRooms() {
        return readRooms("FinalGame_Project/room.txt");
    }

    // flexible method to allow custom path
    public Map<String, Room> readRooms(String filePath) {
        Map<String, Room> roomMap = new HashMap<>();
        Map<String, String> roomExits = new HashMap<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",", 6);
                String roomId = parts[0].trim();
                String roomName = parts[1].trim();
                String roomDescription = parts[2].trim();

                Room room = new Room(roomId, roomName, roomDescription);
                roomMap.put(roomId, room);

                if (parts.length == 6) {
                    String locked = parts[4].trim().toLowerCase();
                    String unlockItem = parts[5].trim().toLowerCase();
                    if (locked.equalsIgnoreCase("lock")) {
                        room.setRoomIsLocked(true);
                        room.setUnlockItem(unlockItem);
                    }
                }

                if (parts.length > 3) {
                    roomExits.put(roomId, parts[3].trim());
                }
            }
            bufferedReader.close();

            for (Map.Entry<String, String> entry : roomExits.entrySet()) {
                String roomID = entry.getKey();
                String[] exitSplit = entry.getValue().split("\\|");
                Room currentRoom = roomMap.get(roomID);

                for (String exit : exitSplit) {
                    String[] exitParts = exit.split("->");
                    if (exitParts.length == 2) {
                        String direction = exitParts[0].trim().toUpperCase();
                        String targetRoom = exitParts[1].trim();
                        Room destination = roomMap.get(targetRoom);
                        if (destination != null) {
                            currentRoom.setExits(direction, destination);
                        } else {
                            System.err.println("⚠ Exit room " + targetRoom + " not found for " + roomID);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return roomMap;
    }

    // Shayla's Item Loader
    public static void loadItems(String filePath, Map<String, Room> rooms) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 7) continue;

                int itemId = Integer.parseInt(parts[0].trim());
                String itemName = parts[1].trim();
                String itemType = parts[2].trim().toLowerCase();
                int itemStat = Integer.parseInt(parts[3].trim());
                String itemDescription = parts[4].trim();
                String roomId = parts[5].trim();
                int quantity = Integer.parseInt(parts[6].trim());

                Room room = rooms.get(roomId);
                if (room == null) {
                    System.err.println("Room not found for ID: " + roomId);
                    continue;
                }

                for (int i = 0; i < quantity; i++) {
                    Items item;
                    switch (itemType) {
                        case "consumable" -> item = new Consumables(itemId, itemName, itemType, itemStat, itemDescription, roomId, 1, itemStat);
                        case "ammunition" -> item = new Ammunition(itemId, itemName, itemType, itemStat, itemDescription, roomId, 1, itemStat);
                        default -> item = new Items(itemId, itemName, itemType, itemStat, itemDescription, roomId, 1);
                    }
                    room.getRoomInventory().add(item);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading items: " + e.getMessage());
        }
    }
}
