package view;

import model.Items;

import java.util.Map;

/**
 * Shayla
 *
 * This is the ItemView class
 *
 *
 */
public class ItemView {

    public void displaySuccess(Items item) {
        System.out.println("You picked up: " + item.getName());
    }

    public void displayFailure(String itemName) {
        System.out.println(itemName + " isn't in this room.");
    }

    public void displayNoItemsInRoom() {
        System.out.println("There are no items located here.");
    }

    public void displayRoomItems(Map<String, Integer> itemCount, Map<String, String> itemDescriptions) {
        System.out.println("📦 Items in this room:");
        for (String name : itemCount.keySet()) {
            System.out.println("--> " + name + " [" + itemCount.get(name) + "x]: " + itemDescriptions.get(name));
        }
    }

    public void displayConsumed(String name, int healed, int newHealth) {
        System.out.println("You used " + name + " and healed " + healed + " health. Current HP: " + newHealth);
    }

    public void displayNotConsumable(String name) {
        System.out.println(name + " is not something you can consume.");
    }

    public void displayMissingConsumable() {
        System.out.println("Specify an item to consume.");
    }

    public void displayDropped(String name){
        System.out.println("You dropped: " + name);
    }

    public void displayMultiDrop(String itemName, int quantity){
        System.out.println("You dropped: " + quantity + "x " + itemName);
    }

    public void displayNoItemToDrop(String name) {
        System.out.println("You don't have " + name + " to drop.");
    }

    public void displayMissingItemToBeDropped() {
        System.out.println("What item did you want to drop?");
    }

    public void displayPickedUpAmount(String name, int quantity) {
        System.out.println("You picked up " + quantity + " of " + name + ".");
    }

    public void displayInventoryFull(int limit) {
        System.out.println("You can't carry more than " + limit + " items total. (hint: drop something)");
    }
}
