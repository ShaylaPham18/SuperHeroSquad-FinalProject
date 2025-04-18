//Re-push to confirm teammate sync
package controller;

import model.Monster;
import model.Player;
import model.Room;
import view.MonsterView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jose Montejo
 * MonsterSpawnManager class
 * Responsible for managing monster spawning in rooms based on spawn conditions.
 * Implements FR3.4 (Spawn Monster) by tracking which monsters have been spawned
 * and ensuring each monster only spawns once per room.
 */
public class MonsterSpawnManager {
    private final Map<String, List<Monster>> monstersByLocation;
    private final Map<String, Boolean> spawnedMonsters; // Tracks which monsters have been spawned
    private final MonsterView monsterView;
    private final Map<String, Monster> activeMonsters; // Tracks monsters that are present in rooms but not defeated

    /**
     * Jose Montejo
     * MonsterSpawnManager Constructor
     * Initializes the spawn manager with a map of monsters organized by location.
     *
     * @param monstersByLocation Map of room IDs to lists of monsters that can spawn there
     */
    public MonsterSpawnManager(Map<String, List<Monster>> monstersByLocation) {
        this.monstersByLocation = monstersByLocation;
        this.spawnedMonsters = new HashMap<>();
        this.activeMonsters = new HashMap<>();
        this.monsterView = new MonsterView();
    }

    /**
     * Jose Montejo
     * checkForMonsterEncounter
     * Checks if a monster should spawn in the current room and handles the encounter.
     * Implements FR3.4 by ensuring each monster only spawns once per game.
     *
     * @param player The player entering the room
     * @param currentRoom The room being entered
     * @param previousRoomID The ID of the room the player was in before
     * @return true if combat occurred and player defeated the monster, false otherwise
     */
    public boolean checkForMonsterEncounter(Player player, Room currentRoom, String previousRoomID) {
        String roomID = currentRoom.getRoomID();
        boolean monsterHandled = false;
        // Check if there's already an active monster in this room
        if (activeMonsters.containsKey(roomID)) {
            Monster activeMonster = activeMonsters.get(roomID);

            // Display monster presence
            System.out.println("\nThe " + activeMonster.getName() + " is still here!");
            System.out.println(activeMonster.getDescription());
            if (activeMonster.getSpecialRule() != null && !activeMonster.getSpecialRule().equals("null")) {
                System.out.println("Special: " + activeMonster.getSpecialRule());
            }
            System.out.println("Your health: " + player.getHealth() + " | " + activeMonster.getName() + "'s health: " + activeMonster.getHealth());
            monsterHandled = true;
            System.out.println("Do you want to fight the " + activeMonster.getName() + "? (yes/no)");

            // Get player's choice
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("yes") || choice.equals("y") || choice.equals("fight")) {
                // Handle the monster encounter
                boolean monsterDefeated = handleMonsterEncounter(activeMonster, player, previousRoomID);

                // If monster is defeated, remove it from active monsters
                if (monsterDefeated) {
                    activeMonsters.remove(roomID);
                }

                // If player fled, move them back to the previous room
                if (!monsterDefeated && player.getHealth() > 0) {
                    return false;
                }

                return monsterDefeated;
            } else {
                System.out.println("You decide not to engage the " + activeMonster.getName() + " for now.");
                return false;
            }
        }

        if (monsterHandled) {
            return false;  // Skip monster spawning if we already handled a monster
        }

        // Check if there are monsters that can spawn in this room
        boolean hasMonsters = false;
        List<Monster> potentialMonsters = null;

        // Try exact match first
        if (monstersByLocation.containsKey(roomID)) {
            potentialMonsters = monstersByLocation.get(roomID);
            hasMonsters = true;
        }
        // If no exact match, try to match by room name
        else if (currentRoom.getRoomName().contains("ICU") ||
                currentRoom.getRoomName().contains("Intensive Care Unit")) {
            potentialMonsters = monstersByLocation.get("3icu");
            hasMonsters = potentialMonsters != null && !potentialMonsters.isEmpty();
            System.out.println("Matched ICU room by name instead of ID");
        }
        // Specific checks for Boiler Room and Operating Room
        else if (currentRoom.getRoomName().contains("Boiler Room")) {
            // Try to find monsters for Boiler Room by ID or name
            potentialMonsters = monstersByLocation.get("0br");  // Use the actual room ID from your debug output
            if (potentialMonsters == null || potentialMonsters.isEmpty()) {
                // Try alternative IDs that might be used
                potentialMonsters = monstersByLocation.get("boiler");
                if (potentialMonsters == null || potentialMonsters.isEmpty()) {
                    potentialMonsters = monstersByLocation.get("boilerroom");
                }
            }
            hasMonsters = potentialMonsters != null && !potentialMonsters.isEmpty();
            System.out.println("Matched Boiler Room by name");
        }
        else if (currentRoom.getRoomName().contains("Operating Room")) {
            // Try to find monsters for Operating Room by ID or name
            potentialMonsters = monstersByLocation.get("3or");  // Use the actual room ID from your debug output
            if (potentialMonsters == null || potentialMonsters.isEmpty()) {
                // Try alternative IDs that might be used
                potentialMonsters = monstersByLocation.get("operating");
                if (potentialMonsters == null || potentialMonsters.isEmpty()) {
                    potentialMonsters = monstersByLocation.get("operatingroom");
                }
            }
            hasMonsters = potentialMonsters != null && !potentialMonsters.isEmpty();
            System.out.println("Matched Operating Room by name");
        }

        if (!hasMonsters || potentialMonsters == null || potentialMonsters.isEmpty()) {
            return false;
        }
        System.out.println("DEBUG: Checking for monsters in room: " + roomID + " (" + currentRoom.getRoomName() + ")");
        System.out.println("DEBUG: Found " + potentialMonsters.size() + " potential monsters for this room");

        // Select a monster from the list
        for (Monster monster : potentialMonsters) {
            // Skip if we've already handled a monster in this room
            if (monsterHandled) {
                return false;
            }
            String monsterKey = monster.getName() + "_" + roomID;

            // Check if this monster has already been spawned but not added to active monsters
            if (spawnedMonsters.containsKey(monsterKey) && spawnedMonsters.get(monsterKey)) {
                // Monster has been spawned before but not added to active monsters
                // This happens when the player returns to a room with a monster

                // Add to active monsters
                activeMonsters.put(roomID, monster);

                // Display monster presence
                System.out.println("\nThe " + monster.getName() + " is still here!");
                System.out.println(monster.getDescription());
                if (monster.getSpecialRule() != null && !monster.getSpecialRule().equals("null")) {
                    System.out.println("Special: " + monster.getSpecialRule());
                }
                System.out.println("Your health: " + player.getHealth() + " | " + monster.getName() + "'s health: " + monster.getHealth());
                monsterHandled = true;
                System.out.println("Do you want to fight the " + monster.getName() + "? (yes/no)");

                // Get player's choice
                java.util.Scanner scanner = new java.util.Scanner(System.in);
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("yes") || choice.equals("y") || choice.equals("fight")) {
                    // Handle the monster encounter
                    boolean monsterDefeated = handleMonsterEncounter(monster, player, previousRoomID);

                    // If monster is defeated, remove it from active monsters
                    if (monsterDefeated) {
                        activeMonsters.remove(roomID);
                    }

                    // If player fled, move them back to the previous room
                    if (!monsterDefeated && player.getHealth() > 0) {
                        return false;
                    }

                    return monsterDefeated;
                } else {
                    System.out.println("You decide not to engage the " + monster.getName() + " for now.");
                    return false;
                }
            }

            // Force spawn for Operating Room
            // Force spawn for critical rooms (Operating Room, Boiler Room, Experiment Room, etc.)
            if (!spawnedMonsters.containsKey(monsterKey) && (
                    roomID.equals("3or") || currentRoom.getRoomName().contains("Operating Room") ||
                            roomID.equals("0br") || currentRoom.getRoomName().contains("Boiler Room") ||
                            roomID.equals("0expr") || currentRoom.getRoomName().contains("Experiment Room") ||
                            roomID.equals("3icu") || currentRoom.getRoomName().contains("ICU") ||
                            roomID.equals("1cafe") || currentRoom.getRoomName().contains("Cafeteria") ||
                            roomID.equals("3lab") || currentRoom.getRoomName().contains("Laboratory") ||
                            roomID.equals("2rad") || currentRoom.getRoomName().contains("Radiology") ||
                            roomID.equals("1wait") || currentRoom.getRoomName().contains("Waiting Area") ||
                            roomID.equals("2ped") || currentRoom.getRoomName().contains("Pediatrics"))) {
                spawnedMonsters.put(monsterKey, true);

                // Add to active monsters
                activeMonsters.put(roomID, monster);

                // Display monster presence
                System.out.println("\nA " + monster.getName() + " appears!");
                System.out.println(monster.getDescription());
                if (monster.getSpecialRule() != null && !monster.getSpecialRule().equals("null")) {
                    System.out.println("Special: " + monster.getSpecialRule());
                }
                System.out.println("Your health: " + player.getHealth() + " | " + monster.getName() + "'s health: " + monster.getHealth());
                System.out.println("Do you want to fight the " + monster.getName() + "? (yes/no)");

                // Get player's choice
                java.util.Scanner scanner = new java.util.Scanner(System.in);
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("yes") || choice.equals("y") || choice.equals("fight")) {
                    // Handle the monster encounter
                    boolean monsterDefeated = handleMonsterEncounter(monster, player, previousRoomID);

                    // If monster is defeated, remove it from active monsters
                    if (monsterDefeated) {
                        activeMonsters.remove(roomID);
                    }

                    // If player fled, move them back to the previous room
                    if (!monsterDefeated && player.getHealth() > 0) {
                        return false;
                    }

                    return monsterDefeated;
                } else {
                    System.out.println("You decide not to engage the " + monster.getName() + " for now.");
                    return false;
                }
            }

            // Check spawn chance for new monsters
            if (monster.shouldSpawn()) {
                // Mark this monster as spawned
                spawnedMonsters.put(monsterKey, true);

                // Add to active monsters
                activeMonsters.put(roomID, monster);

                // Display monster presence
                System.out.println("\nA " + monster.getName() + " appears!");
                System.out.println(monster.getDescription());
                if (monster.getSpecialRule() != null && !monster.getSpecialRule().equals("null")) {
                    System.out.println("Special: " + monster.getSpecialRule());
                }
                System.out.println("Your health: " + player.getHealth() + " | " + monster.getName() + "'s health: " + monster.getHealth());
                System.out.println("Do you want to fight the " + monster.getName() + "? (yes/no)");

                // Get player's choice
                java.util.Scanner scanner = new java.util.Scanner(System.in);
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("yes") || choice.equals("y") || choice.equals("fight")) {
                    // Handle the monster encounter
                    boolean monsterDefeated = handleMonsterEncounter(monster, player, previousRoomID);

                    // If monster is defeated, remove it from active monsters
                    if (monsterDefeated) {
                        activeMonsters.remove(roomID);
                    }

                    // If player fled, move them back to the previous room
                    if (!monsterDefeated && player.getHealth() > 0) {
                        return false;
                    }

                    return monsterDefeated;
                } else {
                    System.out.println("You decide not to engage the " + monster.getName() + " for now.");
                    return false;
                }
            }
        }

        return false; // No monster spawned
    }

    /**
     * Jose Montejo
     * handleMonsterEncounter
     * Manages a monster encounter by creating a controller and starting combat.
     *
     * @param monster The monster to encounter
     * @param player The player encountering the monster
     * @param previousRoomID The ID of the room the player was in before
     * @return true if player defeated the monster, false otherwise
     */
    private boolean handleMonsterEncounter(Monster monster, Player player, String previousRoomID) {
        // Create a controller for this encounter
        MonsterController controller = new MonsterController(monster, player, monsterView, previousRoomID);

        // Start the encounter
        boolean monsterDefeated = controller.encounterMonster();

        // If monster is defeated, remove it from active monsters
        if (monsterDefeated) {
            for (Map.Entry<String, Monster> entry : activeMonsters.entrySet()) {
                if (entry.getValue() == monster) {
                    activeMonsters.remove(entry.getKey());
                    break;
                }
            }
        }

        return monsterDefeated;
    }



    /**
     * Jose Montejo
     * resetMonsterSpawns
     * Resets the spawn status of all monsters, allowing them to spawn again.
     * Useful for testing or if implementing a new game+ feature.
     */
    public void resetMonsterSpawns() {
        spawnedMonsters.clear();
        activeMonsters.clear();
    }
    /**
     * Jose Montejo
     * getSpawnedMonsterCount
     * Returns the number of monsters that have spawned so far.
     * Useful for tracking game progress or achievements.
     *
     * @return The number of monsters that have spawned
     */
    public int getSpawnedMonsterCount() {
        return (int) spawnedMonsters.values().stream().filter(Boolean::booleanValue).count();
    }
}
