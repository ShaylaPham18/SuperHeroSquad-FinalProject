package view;

import model.Items;

import java.util.Map;

/**
 * SHAYLA
 *
 * This is the ItemView class
 * Outputs all the user outputs that relate to items
 * Commands: take, use, drop, inventory
 *
 */
public class ItemView {

    /**
     * When the player is able to pick up an item
     *
     * @param item picked up my the player
     */
    public void displaySuccess(Items item) {
        System.out.println("You picked up: " + item.getName());
    }

    /**
     * The item is not in the room, they tried to pick it up anyway
     *
     * @param itemName whatever the player tried to pick up but could not
     */
    public void displayFailure(String itemName) {
        System.out.println(itemName + " isn't in this room.");
    }

    /**
     * If a player inspects the room and there are no items
     */
    public void displayNoItemsInRoom() {
        System.out.println("There are no items located here.");
    }

    /**
     * Shows a list of all items in the current room (counts and descriptions)
     *
     * @param itemCount a map of the item names to their amount
     * @param itemDescriptions a map of the item descriptions
     */
    public void displayRoomItems(Map<String, Integer> itemCount, Map<String, String> itemDescriptions) {
        System.out.println("📦 Items in this room:");
        for (String name : itemCount.keySet()) {
            System.out.println("--> " + name + " [" + itemCount.get(name) + "x]: " + itemDescriptions.get(name));
        }
    }

    /**
     * Player uses a consumable and gains health
     *
     * @param name of consumable used
     * @param healed amount of health restored
     * @param newHealth newHealth
     */
    public void displayConsumed(String name, int healed, int newHealth) {
        System.out.println("You used " + name + " and healed " + healed + " health. Current HP: " + newHealth);
    }

    /**
     * Player tries to use a non-consumable
     *
     * @param name non-consumables
     */
    public void displayNotConsumable(String name) {
        System.out.println(name + " is not something you can consume.");
    }

    /**
     * Shown when the player types "use" without specifying which item
     */
    public void displayMissingConsumable() {
        System.out.println("Specify an item to consume.");
    }

    /**
     * Player drops a single item from their inventory.
     *
     * @param name item dropped
     */
    public void displayDropped(String name){
        System.out.println("You dropped: " + name);
    }

    /**
     * Player drops multiple of the same item.
     *
     * @param itemName item name
     * @param quantity amt dropped
     */
    public void displayMultiDrop(String itemName, int quantity){
        System.out.println("You dropped: " + quantity + "x " + itemName);
    }

    /**
     * Player attempts to drop an item not in their inventory.
     *
     * @param name missing item
     */
    public void displayNoItemToDrop(String name) {
        System.out.println("You don't have " + name + " to drop.");
    }

    /**
     * When the player types "drop" without specifying an item.
     */
    public void displayMissingItemToBeDropped() {
        System.out.println("What item did you want to drop?");
    }

    /**
     * Player picks up multiple of the same item
     *
     * @param name item name
     * @param quantity amount picked
     */
    public void displayPickedUpAmount(String name, int quantity) {
        System.out.println("You picked up " + quantity + " of " + name + ".");
    }

    /**
     * When the player reaches the maximum allowed inventory size.
     *
     * @param limit max amt of items
     */
    public void displayInventoryFull(int limit) {
        System.out.println("You can't carry more than " + limit + " items total. (hint: drop something)");
    }
}
