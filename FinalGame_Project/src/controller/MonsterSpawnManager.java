package controller;

import model.Monster;
import model.Player;
import model.Room;
import view.MonsterView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    private final Random random;
    private final MonsterView monsterView;

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
        this.random = new Random();
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

        // Check if there are monsters that can spawn in this room
        if (!monstersByLocation.containsKey(roomID)) {
            return false; // No monsters can spawn here
        }

        // Get list of potential monsters for this room
        List<Monster> potentialMonsters = monstersByLocation.get(roomID);
        if (potentialMonsters.isEmpty()) {
            return false; // No monsters left to spawn
        }

        // Select a random monster from the list that hasn't spawned yet
        for (Monster monster : potentialMonsters) {
            String monsterKey = monster.getName() + "_" + roomID;

            // Skip if this monster has already spawned
            if (spawnedMonsters.containsKey(monsterKey) && spawnedMonsters.get(monsterKey)) {
                continue;
            }

            // Check spawn chance
            if (monster.shouldSpawn()) {
                // Mark this monster as spawned
                spawnedMonsters.put(monsterKey, true);

                // Handle the monster encounter
                return handleMonsterEncounter(monster, player, previousRoomID);
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
        return controller.encounterMonster();
    }

    /**
     * Jose Montejo
     * resetMonsterSpawns
     * Resets the spawn status of all monsters, allowing them to spawn again.
     * Useful for testing or if implementing a new game+ feature.
     */
    public void resetMonsterSpawns() {
        spawnedMonsters.clear();
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
