package controller;

import model.Items;
import model.Player;
import model.Room;
import view.ItemView;

import java.util.*;

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
    public void takeItem(String itemName, Room currentRoom, int quantity) {
        List<Items> items = currentRoom.getRoomInventory();
        List<Items> sameItems = new ArrayList<>();
        for (Items item : items) {
            // Jose Montejo: Modified to check if item name contains the input text (case insensitive)
            // This allows "take glock" to work for "Glock 30" items
            if (item.getName().toLowerCase().contains(itemName.toLowerCase())) {
                sameItems.add(item);
            }
        }

        //Item view
        if (sameItems.isEmpty()) {
            view.displayFailure(itemName);
            return;
        }
        // Rest of the method remains unchanged
        Items firstMatch = sameItems.get(0);
        String type = firstMatch.getClass().getSimpleName().toLowerCase();
        String baseType = firstMatch.getClass().getSuperclass().getSimpleName().toLowerCase();
        if (type.contains("consumable") || type.contains("ammunition")) {
            int actualQuantity = Math.min(quantity, sameItems.size());
            for (int i = 0; i < actualQuantity; i++) {
                Items itemToTake = sameItems.get(i);
                player.takeItem(itemToTake);
                items.remove(itemToTake);
            }
            view.displayPickedUpAmount(itemName, actualQuantity);
        } else {
            Items itemToTake = sameItems.get(0);
            player.takeItem(itemToTake);
            items.remove(itemToTake);
            view.displaySuccess(itemToTake);
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
            if (i.getName().toLowerCase().contains(itemName.toLowerCase())) {
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

    public void dropItem(String itemName, Room currentRoom, int quantity) {
        if (itemName == null || itemName.isBlank()) {
            view.displayMissingItemToBeDropped();
            return;
        }
        List<Items> inventory = player.getInventory();
        List<Items> sameItems = new ArrayList<>();
        for (Items item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                sameItems.add(item);
            }
        }
        if (sameItems.isEmpty()) {
            view.displayNoItemToDrop(itemName);
            return;
        }
        Items sample = sameItems.get(0);
        String type = sample.getClass().getSimpleName().toLowerCase();
        if (type.contains("consumable") || type.contains("ammunition")) {
            int actualQuantity = Math.min(quantity, sameItems.size());
            for (int i = 0; i < actualQuantity; i++) {
                Items toDrop = sameItems.get(i);
                inventory.remove(toDrop);
                currentRoom.getRoomInventory().add(toDrop);
            }
            view.displayMultiDrop(itemName, quantity);
        } else {
            Items toDrop = sameItems.get(0);
            inventory.remove(toDrop);
            currentRoom.getRoomInventory().add(toDrop);
            view.displayDropped(toDrop.getName());
        }
    }
}