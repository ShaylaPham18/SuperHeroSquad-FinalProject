package controller;

import model.Items;
import model.Player;
import model.Room;
import view.ItemView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//Shayla
public class ItemController {

    private final ItemView view;
    private final Scanner scanner;
    private final Player player;

    public ItemController(Scanner scanner, ItemView view, Player player) {
        this.scanner = scanner;
        this.view = view;
        this.player = player;
    }

    //take
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

    //Consuming a consumable
    public void consumeItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            view.displayMissingConsumable();
            return;
        }
        Items item = null;
        for (Items i : player.getInventory()) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                item = i;
                break;
            }
        }
        if (item == null) {
            view.displayFailure(itemName);
            return;
        }
        if (item instanceof model.Consumables consumable) {
            int healed = consumable.calculateHealthHealed(player.getHealth());
            int newHealth = player.getHealth() + healed;
            player.setHealth(newHealth);
            //Removes the item from inventory
            player.getInventory().remove(item);
            view.displayConsumed(item.getName(), healed, newHealth);
        } else {
            view.displayNotConsumable(item.getName());
        }
    }

    public void dropItem(String itemName, Room currentRoom) {
        if (itemName == null || itemName.isBlank()) {
            view.displayMissingItemToBeDropped();
            return;
        }
        Items target = null;
        for (Items item : player.getInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                target = item;
                break;
            }
        }
        if (target != null) {
            player.getInventory().remove(target);
            currentRoom.getRoomInventory().add(target);
            view.displayDropped(target.getName());
        } else {
            view.displayNoItemToDrop(itemName);
        }
    }

}
