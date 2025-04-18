package controller;

import model.Items;
import model.Player;
import model.Room;
import model.Monster;

import java.io.*;
import java.util.List;
import java.util.Map;

public class GameSaver {
    private final Player player;

    public GameSaver(Player player) {
        this.player = player;
    }

    public void saveGame(String filename, List<Room> rooms, List<Monster> monsters) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(player.getName());
            writer.newLine();
            writer.write(player.getCurrentRoom().getRoomID());
            writer.newLine();

            for (Items item : player.getInventory()) {
                writer.write(item.getName() + "," + item.getType());
                writer.newLine();
            }

            System.out.println("💾 Game successfully saved!");
        } catch (IOException e) {
            System.out.println("❌ Error saving game: " + e.getMessage());
        }
    }

    public static Player loadGame(String filename, Map<String, Room> rooms, List<Monster> monsters) {
        Player loadedPlayer = new Player("Unknown");

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String name = reader.readLine();
            String currentRoomId = reader.readLine();

            loadedPlayer.setName(name);
            Room room = rooms.get(currentRoomId);
            if (room != null) {
                loadedPlayer.setCurrentRoom(room);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String itemName = parts[0].trim();
                    String itemType = parts[1].trim();
                    Items item = new Items(0, itemName, itemType, 0, "Restored item", currentRoomId, 1);
                    loadedPlayer.pickupItem(item);
                }
            }

            System.out.println("✅ Game successfully loaded!");

        } catch (IOException e) {
            System.out.println("❌ Failed to load game: " + e.getMessage());
        }

        return loadedPlayer;
    }
}
