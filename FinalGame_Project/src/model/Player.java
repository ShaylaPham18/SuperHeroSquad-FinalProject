package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//justin razan shayla jose

/**
 * Player class: handles player, health, location, base damage, and inventory
 */
public class Player {
    private String name;
    private int health;
    private int basePlayerDamage = 5;
    private final ArrayList<Items> inventory;
    private Room currentRoom;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.inventory = new ArrayList<>();
        this.currentRoom = null; // starting room set later
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void takeItem(Items item) {
        inventory.add(item);
    }

    public int getBasePlayerDamage() {
        return basePlayerDamage;
    }

//    public void setBasePlayerDamage(int basePlayerDamage) {
//        this.basePlayerDamage = basePlayerDamage;
//    }

    //justin
    public void showStats() {
        System.out.println("Player health: " + health + "\nPlayer damage: " + basePlayerDamage);
    }

    //Jose Montejo

    /**
     * getInventory
     * Returns the player's current inventory as an ArrayList of Items.
     * Used by MonsterController to check for weapons during combat.
     *
     * @return ArrayList<Items> containing all items in the player's inventory
     */
    public ArrayList<Items> getInventory() {
        return inventory;
    }

    // ✅ New method for checking if player has a specific item

    public boolean hasItem(String itemName) {
        for (Items item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    // Check the inventory --> Razan, Shayla

    /**
     * Shows all items in the player inventory
     * Grouping identical consumables and ammo into one description with quantity shown
     */
    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("🎒 Your inventory is empty.");
        } else {
            System.out.println("🎒 Your inventory contains:");

            //Needed to track the quantity and descriptions
            //Count them
            //Display of grouped items and also unique items
            Map<String, Integer> itemCount = new HashMap<>();
            Map<String, String> itemDescriptions = new HashMap<>();
            for (Items item : inventory) {
                String name = item.getName();
                itemCount.put(name, itemCount.getOrDefault(name, 0) + 1);
                itemDescriptions.putIfAbsent(name, item.getDescription());
            }
            for (String name : itemCount.keySet()) {
                int count = itemCount.get(name);
                String description = itemDescriptions.get(name);
                if (count > 1) {
                    System.out.println("- " + name + " [" + count + "x]: " + description);
                } else {
                    System.out.println("- " + name + ": " + description);
                }
            }
        }
    }
}