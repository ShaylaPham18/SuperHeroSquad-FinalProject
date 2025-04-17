package view;

import java.util.List;
import model.Monster;

/**
 * Jose Montejo
 * MonsterView class
 * Responsible for displaying monster-related information to the user.
 * Handles all console output related to monster encounters, combat,
 * and status updates.
 */
public class MonsterView {

    /**
     * Jose Montejo
     * displayMonsterEncounter
     * Shows the initial encounter message when a player meets a monster.
     * Displays the monster's name, description, and special rule if applicable.
     */
    public void displayMonsterEncounter(Monster monster) {
        System.out.println("\nA " + monster.getName() + " appears!");
        System.out.println(monster.getDescription());

        if (monster.getSpecialRule() != null) {
            System.out.println("Special: " + monster.getSpecialRule());
        }
    }

    /**
     * Jose Montejo
     * displayPlayerAttack
     * Shows the result of a player's attack on a monster.
     * Displays damage dealt and the monster's remaining health.
     */
    public void displayPlayerAttack(String weapon, int damage, Monster monster) {
        System.out.println("You attack the " + monster.getName() + " with your " + weapon + " for " + damage + " damage!");
        System.out.println("The " + monster.getName() + " has " + monster.getHealth() + " health remaining.");
    }

    /**
     * Jose Montejo
     * displayMonsterAttack
     * Shows the result of a monster's attack on the player.
     * Includes special messages for critical hits or special abilities.
     */
    public void displayMonsterAttack(Monster monster, int damage) {
        System.out.println("The " + monster.getName() + " attacks you for " + damage + " damage!");

        // Display special rule messages
        if (monster.getName().equals("Amalgamation") && damage == monster.getDamageToPlayer() * 2) {
            System.out.println("CRITICAL HIT! The Amalgamation deals double damage!");
        } else if (monster.getName().equals("Facehugger")) {
            System.out.println("The Facehugger deals 2 extra damage with its attack!");
        } else if (monster.getName().equals("Spitter")) {
            System.out.println("The Spitter's attack causes additional damage from infection!");
        }
    }

    /**
     * Jose Montejo
     * displayDodge
     * Shows a message when a monster dodges an attack (Zombie Dog special ability).
     */
    public void displayDodge(Monster monster) {
        System.out.println("The " + monster.getName() + " dodges your attack!");
    }

    /**
     * Jose Montejo
     * displayMonsterDefeated
     * Shows a message when a monster is defeated.
     */
    public void displayMonsterDefeated(Monster monster) {
        System.out.println("You defeated the " + monster.getName() + "!");
    }

    /**
     * Jose Montejo
     * displayPlayerDefeated
     * Shows a message when the player is defeated by a monster.
     */
    public void displayPlayerDefeated() {
        System.out.println("You have been defeated!");
    }

    /**
     * Jose Montejo
     * displayCombatOptions
     * Shows the available combat options to the player.
     */
    public void displayCombatOptions() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Attack with weapon");
        System.out.println("2. Use item");
        System.out.println("3. Flee");
    }

    /**
     * Jose Montejo
     * displayWeaponSelection
     * Shows the available weapons for the player to choose from.
     */
    public void displayWeaponSelection(List<String> weapons) {
        System.out.println("\nSelect a weapon:");
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println((i + 1) + ". " + weapons.get(i));
        }
    }

    /**
     * Jose Montejo
     * displayFleeResult
     * Shows the result of a player's attempt to flee from combat.
     */
    public void displayFleeResult(boolean success) {
        if (success) {
            System.out.println("You successfully fled from the monster!");
        } else {
            System.out.println("You failed to flee! The monster attacks you!");
        }
    }
}
