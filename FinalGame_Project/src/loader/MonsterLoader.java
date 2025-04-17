package loader;

import model.Monster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jose Montejo
 * MonsterLoader class
 * Responsible for loading monster data from external text files and organizing
 * monsters by their spawn locations. This class follows the data-driven approach
 * by reading monster specifications from files instead of hard-coding them.
 */
public class MonsterLoader {

    /**
     * Jose Montejo
     * loadMonsters
     * Reads monster data from a specified file and creates Monster objects.
     * Parses each line of the file to extract monster attributes including name,
     * description, health, damage, weapon weaknesses, and special rules.
     *
     * @param filename The path to the monsters.txt file
     * @return A list of Monster objects created from the file data
     */
    public static List<Monster> loadMonsters(String filename) {
        List<Monster> monsters = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // Skip header line if it exists
            if ((line = reader.readLine()) != null && line.startsWith("name")) {
                // Skip header - intentionally empty
            }

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length >= 9) {
                    String name = parts[0].trim();
                    String description = parts[1].trim();
                    int health = Integer.parseInt(parts[2].trim());
                    int damageToPlayer = Integer.parseInt(parts[3].trim());

                    // Parse weapon weaknesses
                    String[] weaponWeaknesses = parts[4].trim().split(",");

                    // Parse weapon damages
                    String[] damageStrings = parts[5].trim().split(",");
                    int[] weaponDamages = new int[damageStrings.length];
                    for (int i = 0; i < damageStrings.length; i++) {
                        weaponDamages[i] = Integer.parseInt(damageStrings[i].trim());
                    }

                    int spawnChance = Integer.parseInt(parts[6].trim());
                    String spawnLocation = parts[7].trim();
                    String specialRule = parts[8].trim().equals("null") ? null : parts[8].trim();

                    Monster monster = new Monster(
                            name, description, health, damageToPlayer,
                            weaponWeaknesses, weaponDamages, spawnChance,
                            spawnLocation, specialRule
                    );

                    monsters.add(monster);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading monsters: " + e.getMessage());
        }

        return monsters;
    }

    /**
     * Jose Montejo
     * getMonstersByLocation
     * Organizes monsters by their spawn locations for efficient lookup during gameplay.
     * Creates a map where keys are room IDs and values are lists of monsters
     * that can spawn in those rooms.
     *
     * @param monsters A list of Monster objects to organize
     * @return A map of room IDs to lists of monsters that can spawn in those rooms
     */
    public static Map<String, List<Monster>> getMonstersByLocation(List<Monster> monsters) {
        Map<String, List<Monster>> monstersByLocation = new HashMap<>();

        for (Monster monster : monsters) {
            String location = monster.getSpawnLocation();
            if (!monstersByLocation.containsKey(location)) {
                monstersByLocation.put(location, new ArrayList<>());
            }
            monstersByLocation.get(location).add(monster);
        }

        return monstersByLocation;
    }
}