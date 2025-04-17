package controller;

import model.Items;
import model.Player;
import model.Room;
import view.ItemView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ItemController {

    private final ItemView view;
    private final Scanner scanner;
    private final Player player;

    public ItemController(Scanner scanner, ItemView view, Player player) {
        this.scanner = scanner;
        this.view = view;
        this.player = player;
    }

    public void takeItem(String itemName, Room currentRoom) {
        List<Items> items = currentRoom.getRoomInventory();
        Items target = null;
        for (Items item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                target = item;
                break;
            }
        }
        if (target != null) {
            items.remove(target);
            player.takeItem(target);
            view.displaySuccess(target);
        } else {
            view.displayFailure(itemName);
        }
    }

    public void inspectRoomItems(Room room) {
        if (room.getRoomInventory().isEmpty()) {
            view.displayNoItemsInRoom();
            return;
        }
        Map<String, Integer> itemCount = new HashMap<>();
        Map<String, String> itemDescriptions = new HashMap<>();

        for (Items item : room.getRoomInventory()) {
            itemCount.put(item.getName(), itemCount.getOrDefault(item.getName(), 0) + 1);
            itemDescriptions.putIfAbsent(item.getName(), item.getDescription());
        }
        view.displayRoomItems(itemCount, itemDescriptions);
    }
}
