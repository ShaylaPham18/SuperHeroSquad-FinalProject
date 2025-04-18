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
            if (item.getName().equalsIgnoreCase(itemName)) {
                sameItems.add(item);
            }
        }

        //Item view
        if (sameItems.isEmpty()) {
            view.displayFailure(itemName);
            return;
        }
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
