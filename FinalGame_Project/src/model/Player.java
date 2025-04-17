package model;

import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private ArrayList<Items> inventory;
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

    public void pickupItem(Items item) {
        inventory.add(item);
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
}