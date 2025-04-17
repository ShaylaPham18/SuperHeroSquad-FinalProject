package model;

import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private int basePlayerDamage=5;
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

    public int getBasePlayerDamage() {
        return basePlayerDamage;
    }

    public void setBasePlayerDamage(int basePlayerDamage) {
        this.basePlayerDamage = basePlayerDamage;
    }
    public int totalDamage(){
        //Logic will be if weapon equipped:   basePlayerDamage+weaponDamage
        return basePlayerDamage;
    }

    public void showStats(){
        System.out.println("Player health: "+health+"\nPlayer damage: "+basePlayerDamage);
    }

    //Shayla
    public void consumeItem(String itemName) {
        Items item = null;
        for (Items i : inventory) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                item = i;
                break;
            }
        }
        if (item == null) {//weird error here
            System.err.println("You do not have "+itemName+" in your inventory.");
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
}