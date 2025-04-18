package model;

import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private int basePlayerDamage = 5;
    private ArrayList<Items> inventory;
    private Room currentRoom;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.inventory = new ArrayList<>();
        this.currentRoom = null;
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

    public void dropItem(Items items) {
        inventory.remove(items);
    }

    public int getBasePlayerDamage() {
        return basePlayerDamage;
    }

    public void setBasePlayerDamage(int basePlayerDamage) {
        this.basePlayerDamage = basePlayerDamage;
    }

    public int totalDamage() {
        return basePlayerDamage;
    }

    public void showStats() {
        System.out.println("Player health: " + health + "\nPlayer damage: " + basePlayerDamage);
    }

    public void consumeItem(String itemName) {
        Items item = null;
        for (Items i : inventory) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                item = i;
                break;
            }
        }
        if (item == null) {
            System.err.println("You do not have " + itemName + " in your inventory.");
            return;
        }
        if (item instanceof Consumables) {
            Consumables consume = (Consumables) item;
            int healedAmount = consume.calculateHealthHealed(100);
            health += healedAmount;
            inventory.remove(item);
            System.out.println("You used " + item.getName() + " and healed " + healedAmount + " health. Current Health: " + health);
        } else {
            System.out.println(item.getName() + " is not consumable.");
        }
    }

    public ArrayList<Items> getInventory() {
        return inventory;
    }

    public boolean hasItem(String itemName) {
        for (Items item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("🎒 Your inventory is empty.");
        } else {
            System.out.println("🎒 Your inventory contains:");
            for (Items item : inventory) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }

    // ✅ Add this to fix save/load game
    public void pickupItem(Items item) {
        takeItem(item);
    }
}
