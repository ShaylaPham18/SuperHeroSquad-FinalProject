package view;

import model.Items;

/**
 * Shayla
 *
 */
public class ItemView {
        public void displayItemInformation(Items item) {
            System.out.println(" " + item.getName() + ": " + item.getDescription());
        }

        public void displaySuccess(Items item) {
            System.out.println("You picked up: " + item.getName());
        }

        public void displayFailure(String itemName) {
            System.out.println(itemName + "isn't in this room.");
        }

        public void displayNoItemsInRoom() {
            System.out.println("There are no items located here.");
        }
    }
