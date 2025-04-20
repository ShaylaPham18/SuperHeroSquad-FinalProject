//Re-push to confirm teammate sync
package controller;

import model.Monster;
import model.Player;
import model.Items;
import model.Weapons;
import model.Ammunition;
import loader.WeaponsLoader;
import view.MonsterView;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

/**
 * Jose Montejo
 * MonsterController class
 * Handles the interaction between Monster models and MonsterView.
 * Manages combat mechanics, player-monster interactions, and special abilities.
 * Follows MVC pattern by separating game logic from display and data.
 */
public class MonsterController {
    private final Monster monster;
    private final Player player;
    private final MonsterView view;
    private final Scanner scanner;
    private final Random random;
    private boolean combatActive;
    private final String previousRoomID; // Store previous room ID for flee functionality
    // Map to store weapons loaded from items.txt
    private Map<String, Weapons> weaponsMap = new HashMap<>();
    private Weapons equippedWeapon = null;

    /**
     * Jose Montejo
     * MonsterController Constructor
     * Creates a controller to manage interactions between a monster and the player.
     *
     * @param monster The monster to control
     * @param player The player engaging with the monster
     * @param view The view to display monster-related information
     * @param previousRoomID The ID of the room player was in before encountering monster
     */
    public MonsterController(Monster monster, Player player, MonsterView view, String previousRoomID) {
        this.monster = monster;
        this.player = player;
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.combatActive = false;
        this.previousRoomID = previousRoomID;

        // Load weapons from items.txt
        try {
            // Try multiple possible file paths
            try {
                this.weaponsMap = WeaponsLoader.loadWeapons("FinalGame_Project/monsters.txt");
            } catch (Exception e1) {
                try {
                    this.weaponsMap = WeaponsLoader.loadWeapons("FinalGame_Project/items.txt");
                } catch (Exception e2) {
                    // If both fail, create a hardcoded knife weapon
                    Weapons knife = new Weapons(1, "knife", "weapon", 5, "Surgical knives and scalpels.", "multiple", 1);
                    this.weaponsMap.put("knife", knife);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading weapons: " + e.getMessage());
        }
    }

    /**
     * Jose Montejo
     * encounterMonster
     * Initiates a monster encounter, displaying information and prompting for player action.
     * Returns true if the player defeats the monster, false otherwise.
     *
     * Implements FR3.1 (Flee from Monster) and FR3.2 (Attack Monsters)
     */
    public boolean encounterMonster() {
        // Skip the prompt and go straight to combat
        return startCombat();
    }

    /**
     * Jose Montejo
     * startCombat
     * Manages the combat loop between player and monster.
     * Returns true if the player defeats the monster, false if the player is defeated.
     *
     * Implements FR3.5 (Differentiate commands during combat)
     */
    private boolean startCombat() {
        combatActive = true;
        boolean skipMonsterTurn = false;

        // Jose Montejo: Added check to prevent combat with already defeated monsters
        if (monster.isDefeated() || monster.getHealth() <= 0) {
            view.displayMonsterDefeated(monster);
            return true;
        }

        while (combatActive) {
            // Reset skipMonsterTurn at the start of each loop
            skipMonsterTurn = false;

            // Display combat options
            view.displayCombatOptions();
            // Add display for currently equipped weapon
            if (equippedWeapon != null) {
                System.out.println("Currently equipped: " + equippedWeapon.getName());
            } else {
                System.out.println("Currently equipped: fists");
            }
            String choice = scanner.nextLine().trim().toLowerCase();

            // FR3.5: Only allow specific commands during combat
            switch (choice) {
                case "1":
                case "attack":
                case "att":
                    handlePlayerAttack();

                    // Jose Montejo: Check if monster is defeated only after attack
                    if (monster.getHealth() <= 0) {
                        monster.setDefeated(true);
                        view.displayMonsterDefeated(monster);
                        combatActive = false;
                        return true;
                    }
                    break;
                case "2":
                case "use":
                    handleUseItem();
                    skipMonsterTurn = true; // Skip monster's turn when using an item
                    break;
                case "3":
                case "flee":
                case "run":
                    if (tryToFlee()) {
                        // If flee is successful, we need to move the player back to the previous room
                        // This is handled in GameController by returning false here
                        combatActive = false;
                        return false;
                    }
                    break;
                case "4":
                case "equip":
                    handleEquipWeapon();
                    skipMonsterTurn = true; // Skip monster's turn when equipping
                    break;
                case "help":
                    displayCombatHelp();
                    skipMonsterTurn = true; // Skip monster's turn for help command
                    break;
                default:
                    System.out.println("Invalid combat command. Type 'help' for available combat commands.");
                    skipMonsterTurn = true; // Skip monster's turn for invalid commands
                    continue;
            }

            // Monster attacks if combat is still active
            if (combatActive && !monster.isDefeated() && !skipMonsterTurn) {
                // Remove any special conditions that might prevent certain monsters from attacking
                int damage = monster.attack();

                // Display monster attack with separate messages for base damage and special rule
                if (monster.getName().equals("Facehugger")) {
                    System.out.println("The " + monster.getName() + " attacks you for " + (damage - 2) + " damage!");
                    System.out.println("Special Rule Activates: The Facehugger deals 2 extra damage with its attack!");
                } else if (monster.getName().equals("Spitter")) {
                    System.out.println("The " + monster.getName() + " attacks you for " + (damage - 5) + " damage!");
                    System.out.println("Special Rule Activates: The Spitter deals 5 extra damage from infection!");
                } else if (monster.getName().equals("Zombie Dog") || monster.getName().equals("Abandoned Baby")) {
                    // Ensure Zombie Dog and Abandoned Baby attacks are displayed properly
                    System.out.println("The " + monster.getName() + " attacks you for " + damage + " damage!");
                } else {
                    view.displayMonsterAttack(monster, damage);
                }

                player.setHealth(player.getHealth() - damage);

                // Check if player is defeated
                if (player.getHealth() <= 0) {
                    player.setHealth(0);
                    view.displayPlayerDefeated();
                    combatActive = false;
                    return false;
                }

                // Display current health status
                System.out.println("Your health: " + player.getHealth() + " | " +
                        monster.getName() + "'s health: " + monster.getHealth());
            }
        }

        return monster.isDefeated();
    }

    /**
     * Jose Montejo
     * handleEquipWeapon
     * Allows the player to equip a different weapon during combat
     */
    private void handleEquipWeapon() {
        // Get weapons from inventory
        ArrayList<Items> inventory = player.getInventory();
        ArrayList<Weapons> availableWeapons = new ArrayList<>();

        // Track which weapon types we've already added to avoid duplicates
        boolean knifeAdded = false;
        boolean axeAdded = false;
        boolean glockAdded = false;
        boolean shotgunAdded = false;

        // Find all weapons in inventory
        for (Items item : inventory) {
            String itemName = item.getName().toLowerCase().trim();

            // Special case for known weapons when weaponsMap is empty
            if (weaponsMap.isEmpty()) {
                // Check for knife with more flexible matching
                if ((itemName.contains("knife") || itemName.equals("knife")) && !knifeAdded) {
                    Weapons knife = new Weapons(1, "knife", "weapon", 5, "Surgical knives and scalpels.", "multiple", 1);
                    availableWeapons.add(knife);
                    knifeAdded = true;
                }
                // Check for axe with more flexible matching
                else if ((itemName.contains("axe") || itemName.equals("axe")) && !axeAdded) {
                    Weapons axe = new Weapons(2, "axe", "weapon", 10, "A firefighter's axe with a bloodstained blade.", "multiple", 1);
                    availableWeapons.add(axe);
                    axeAdded = true;
                }
                // Check for glock with more flexible matching
                else if ((itemName.contains("glock") || itemName.equals("glock 30") || itemName.equals("glock")) && !glockAdded) {
                    Weapons glock = new Weapons(3, "Glock 30", "weapon", 8, "A semi-automatic handgun.", "multiple", 1);
                    availableWeapons.add(glock);
                    glockAdded = true;
                }
                // Check for shotgun with more flexible matching
                else if ((itemName.contains("shotgun") || itemName.equals("shotgun")) && !shotgunAdded) {
                    Weapons shotgun = new Weapons(4, "shotgun", "weapon", 15, "A pump-action shotgun with high stopping power.", "multiple", 1);
                    availableWeapons.add(shotgun);
                    shotgunAdded = true;
                }
                continue;
            }

            // Normal weapon lookup if weaponsMap is not empty
            Weapons weapon = weaponsMap.get(itemName);
            if (weapon != null) {
                availableWeapons.add(weapon);
            }
        }

        if (availableWeapons.isEmpty()) {
            System.out.println("You don't have any weapons to equip.");
            return;
        }

        // Display available weapons
        System.out.println("\n=== AVAILABLE WEAPONS ===");
        for (int i = 0; i < availableWeapons.size(); i++) {
            Weapons weapon = availableWeapons.get(i);
            System.out.println((i + 1) + ". " + weapon.getName() + " (Damage: " + weapon.getAttackDmg() + ")");
        }
        System.out.println("0. Unequip (use fists)");
        System.out.print("\nEnter the number of the weapon you want to equip: ");

        String input = scanner.nextLine().trim();

        try {
            int weaponIndex = Integer.parseInt(input);
            if (weaponIndex == 0) {
                equippedWeapon = null;
                System.out.println("You unequipped your weapon and will fight with your fists.");
            } else if (weaponIndex > 0 && weaponIndex <= availableWeapons.size()) {
                equippedWeapon = availableWeapons.get(weaponIndex - 1);
                System.out.println("You equipped the " + equippedWeapon.getName() + ".");
            } else {
                System.out.println("Invalid weapon number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }



    /**
     * Jose Montejo
     * handlePlayerAttack
     * Processes the player's attack action, calculating damage and applying it to the monster.
     *
     * Implements FR3.3 (Increase Damage)
     */
    private void handlePlayerAttack() {
        // Default weapon and damage
        String weapon = "fists";
        int damage = player.getBasePlayerDamage();

        // Use equipped weapon if available
        if (equippedWeapon != null) {
            weapon = equippedWeapon.getName();
            damage = equippedWeapon.getAttackDmg();
        }

        // Calculate actual damage with weapon weakness multiplier
        int actualDamage = damage;
        for (int i = 0; i < monster.getWeaponWeaknesses().length; i++) {
            // Extract the base weapon name without any parenthetical suffix
            String weaponName = weapon;
            if (weaponName.contains("(")) {
                weaponName = weaponName.substring(0, weaponName.indexOf("(")).trim();
            }

            if (monster.getWeaponWeaknesses()[i].equalsIgnoreCase(weaponName)) {
                actualDamage = (int) Math.round(damage * (1.0 + (monster.getWeaponDamages()[i] / 100.0)));
                break;
            }
        }

        // Display player attack message with actual damage
        System.out.println("You attack the " + monster.getName() + " with your " + weapon + " for " + actualDamage + " damage!");

        // Apply damage to monster and check if it dodges (for Zombie Dog)
        boolean hit = monster.takeDamage(weapon, actualDamage);
        if (!hit && monster.getName().equals("Zombie Dog")) {
            view.displayDodge(monster);
            // Display both monster and player health even after dodge
            System.out.println("Your health: " + player.getHealth() + " | " +
                    monster.getName() + "'s health: " + monster.getHealth());
        } else {
            // Display special rule activation for Facehugger
            if (monster.getName().equals("Facehugger")) {
                System.out.println("Special Rule Activates: The Facehugger attacks you for 2 extra damage as you attack!");
                player.setHealth(player.getHealth() - 2);
                // Display player's health after taking damage (remove duplicate)
                System.out.println("Your health: " + player.getHealth());
            }

            // Jose Montejo: Added special rule activation for Spitter on player attack
            if (monster.getName().equals("Spitter")) {
                System.out.println("Special Rule Activates: The Spitter deals 5 extra damage from infection as you attack!");
                player.setHealth(player.getHealth() - 5);
                System.out.println("Your health: " + player.getHealth());
            }

            // Display updated monster health
            System.out.println("The " + monster.getName() + " has " + monster.getHealth() + " health remaining.");
            // Add player health display
            System.out.println("Your health: " + player.getHealth());
        }
    }

    /**
     * Jose Montejo
     * tryToFlee
     * Flees from combat
     * Implements FR3.1 (Flee from Monster)
     */
    private boolean tryToFlee() {
        // Always succeed at fleeing - no RNG
        boolean success = true;
        view.displayFleeResult(success);

        return success;
    }

    /**
     * Jose Montejo
     * displayCombatHelp
     * Shows available combat commands to the player.
     */
    private void displayCombatHelp() {
        System.out.println("\n=== COMBAT COMMANDS ===");
        System.out.println("1 or attack - Attack the monster");
        System.out.println("2 or use - Use an item from your inventory");
        System.out.println("3 or flee/run - Attempt to escape (40% chance)");
        System.out.println("4 or equip - Equip or change weapons");
        System.out.println("help - Display this help message");
        System.out.println("=====================\n");
    }

    /**
     * Get the best weapon from player's inventory
     * @author Jose Montejo
     * This method finds the weapon with the highest damage in the player's inventory
     * @return The best weapon or null if no weapons found
     */
    private Weapons getBestWeaponFromInventory() {
        Weapons bestWeapon = null;
        int highestDamage = 0;

        // Check each item in the player's inventory
        ArrayList<Items> playerInventory = player.getInventory();
        if (playerInventory != null && !playerInventory.isEmpty()) {
            for (Items item : playerInventory) {
                Weapons weapon = weaponsMap.get(item.getName().toLowerCase());
                if (weapon != null && weapon.getAttackDmg() > highestDamage) {
                    bestWeapon = weapon;
                    highestDamage = weapon.getAttackDmg();
                }
            }
        }

        return bestWeapon;
    }

    /**
     * Jose Montejo
     * handleUseItem
     * Processes the player's action to use an item during combat
     */
    private void handleUseItem() {
        // Display player's inventory
        ArrayList<Items> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("You don't have any items to use.");
            return;
        }

        // Filter for only consumable items
        Map<String, Integer> itemCount = new HashMap<>();
        Map<String, ArrayList<Items>> itemGroups = new HashMap<>();

        // Group consumable items by name
        for (Items item : inventory) {
            String name = item.getName();
            String itemNameLower = name.toLowerCase();

            // Only add items that are consumable healing items
            if (itemNameLower.contains("painkiller") ||
                    itemNameLower.contains("first aid") ||
                    itemNameLower.contains("vaccine") ||
                    itemNameLower.contains("gas mask")) {

                itemCount.put(name, itemCount.getOrDefault(name, 0) + 1);
                if (!itemGroups.containsKey(name)) {
                    itemGroups.put(name, new ArrayList<>());
                }
                itemGroups.get(name).add(item);
            }
        }

        // Check if there are any consumable items
        if (itemGroups.isEmpty()) {
            System.out.println("You don't have any usable items.");
            return;
        }

        // Display header with CONSUMABLES instead of YOUR INVENTORY
        System.out.println("\n=== CONSUMABLES ===");

        // Display grouped items
        int index = 1;
        Map<Integer, Items> indexToItem = new HashMap<>();
        for (String name : itemGroups.keySet()) {
            int count = itemCount.get(name);
            if (count > 1) {
                System.out.println(index + ". " + name + " (" + count + "x)");
            } else {
                System.out.println(index + ". " + name);
            }
            indexToItem.put(index, itemGroups.get(name).get(0));
            index++;
        }

        System.out.println("0. Cancel");
        System.out.print("\nEnter the number of the item you want to use: ");

        String input = scanner.nextLine().trim();

        // Check if player wants to cancel
        if (input.equals("0")) {
            System.out.println("You decided not to use any item.");
            return;
        }

        try {
            int itemIndex = Integer.parseInt(input);
            if (itemIndex >= 1 && itemIndex < index) {
                Items selectedItem = indexToItem.get(itemIndex);
                String itemName = selectedItem.getName().toLowerCase();

                // Determine heal amount based on item name from items.txt
                int healAmount = 0;
                if (itemName.contains("painkiller a")) {
                    healAmount = 25;
                } else if (itemName.contains("painkiller b")) {
                    healAmount = 50; // Fixed from 5 to 50 as per SRS
                } else if (itemName.contains("first aid")) {
                    healAmount = 10; // Fixed from 25 to 10 as per SRS
                } else if (itemName.contains("vaccine") || itemName.contains("gas mask")) {
                    healAmount = 100;
                }

                player.setHealth(Math.min(player.getHealth() + healAmount, 100));
                System.out.println("You used " + selectedItem.getName() + " and restored " + healAmount + " health.");

                // Display special rule activation for Facehugger when using items
                if (monster.getName().equals("Facehugger")) {
                    System.out.println("Special Rule Activates: The Facehugger attacks you for 2 extra damage as you use an item!");
                    player.setHealth(player.getHealth() - 2);
                    System.out.println("Your health: " + player.getHealth());
                }
                //Added special rule activation for Spitter when using items
                else if (monster.getName().equals("Spitter")) {
                    System.out.println("Special Rule Activates: The Spitter deals 5 extra damage from infection as you use an item!");
                    player.setHealth(player.getHealth() - 5);
                    System.out.println("Your health: " + player.getHealth());
                }
                else {
                    System.out.println("Your health is now: " + player.getHealth());
                }

                // Remove one instance of the item
                for (Items item : inventory) {
                    if (item.getName().equals(selectedItem.getName())) {
                        inventory.remove(item);
                        break;
                    }
                }
            } else {
                System.out.println("Invalid item number.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }
    }

    /**
     * Jose Montejo
     * getPreviousRoomID
     * Returns the ID of the room the player was in before encountering the monster.
     * Used for implementing the flee functionality.
     */
    public String getPreviousRoomID() {
        return previousRoomID;
    }
}