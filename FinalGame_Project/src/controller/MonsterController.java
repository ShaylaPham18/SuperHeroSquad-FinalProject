package controller;

import model.Monster;
import model.Player;
import model.Items;
import view.MonsterView;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

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
        // Display initial encounter message
        view.displayMonsterEncounter(monster);

        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Attack");
        System.out.println("2. Ignore (try to avoid combat)");
        System.out.print("> ");

        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("1") || choice.equals("attack") || choice.equals("att")) {
            return startCombat();
        } else {
            // Player tries to avoid combat - 50% chance of success
            boolean avoided = random.nextBoolean();
            if (avoided) {
                System.out.println("You successfully avoided the " + monster.getName() + "!");
                return true;
            } else {
                System.out.println("The " + monster.getName() + " attacks you before you can escape!");
                return startCombat();
            }
        }
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

        while (combatActive) {
            // Display combat options
            view.displayCombatOptions();
            String choice = scanner.nextLine().trim().toLowerCase();

            // FR3.5: Only allow specific commands during combat
            switch (choice) {
                case "1":
                case "attack":
                case "att":
                    handlePlayerAttack();
                    break;
                case "2":
                case "use":
                    handleUseItem();
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
                case "help":
                    displayCombatHelp();
                    break;
                default:
                    System.out.println("Invalid combat command. Type 'help' for available combat commands.");
            }

            // Check if monster is defeated
            if (monster.isDefeated()) {
                view.displayMonsterDefeated(monster);
                combatActive = false;
                return true;
            }

            // Monster attacks if combat is still active
            if (combatActive) {
                int damage = monster.attack();
                view.displayMonsterAttack(monster, damage);
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
     * handlePlayerAttack
     * Processes the player's attack action, calculating damage and applying it to the monster.
     *
     * Implements FR3.3 (Increase Damage)
     */
    private void handlePlayerAttack() {
        // Default weapon and damage
        String weapon = "fists";
        int damage = player.getBasePlayerDamage();

        // FR3.3: Check for weapons in inventory to increase damage
        ArrayList<Items> playerInventory = player.getInventory();
        if (playerInventory != null && !playerInventory.isEmpty()) {
            for (Items item : playerInventory) {
                if (item.getName().equalsIgnoreCase("Glock 30")) {
                    weapon = "Glock 30";
                    damage = 8; // As specified in SRS
                    break;
                } else if (item.getName().equalsIgnoreCase("Shotgun")) {
                    weapon = "Shotgun";
                    damage = 15; // As specified in SRS
                    break;
                } else if (item.getName().equalsIgnoreCase("Knife")) {
                    weapon = "Knife";
                    damage = 5; // As specified in SRS
                    break;
                } else if (item.getName().equalsIgnoreCase("Axe")) {
                    weapon = "Axe";
                    damage = 7; // Example value
                    break;
                }
            }
        }

        // Check if monster dodges (for Zombie Dog)
        boolean hit = monster.takeDamage(weapon, damage);

        if (!hit && monster.getName().equals("Zombie Dog")) {
            view.displayDodge(monster);
        } else {
            view.displayPlayerAttack(weapon, damage, monster);
        }
    }

    /**
     * Jose Montejo
     * handleUseItem
     * Allows the player to use an item from their inventory during combat.
     */
    private void handleUseItem() {
        System.out.println("What item would you like to use?");
        String itemName = scanner.nextLine().trim();

        // For consumable items like health packs
        player.consumeItem(itemName);
    }

    /**
     * Jose Montejo
     * tryToFlee
     * Attempts to flee from combat with a 40% chance of success.
     * If successful, the player will be moved back to the previous room.
     * Returns true if successful, false otherwise.
     *
     * Implements FR3.1 (Flee from Monster)
     */
    private boolean tryToFlee() {
        boolean success = random.nextDouble() < 0.4; // 40% chance to flee
        view.displayFleeResult(success);

        if (!success) {
            // Monster gets a free attack if flee fails
            int damage = monster.attack();
            view.displayMonsterAttack(monster, damage);
            player.setHealth(player.getHealth() - damage);
        }

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
        System.out.println("help - Display this help message");
        System.out.println("=====================\n");
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
