package view;

import model.Items;

import java.util.Map;

/**
 * Shayla
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
            System.out.println("--> " + name + " (" + itemCount.get(name) + "x): " + itemDescriptions.get(name));
        }
    }
}
