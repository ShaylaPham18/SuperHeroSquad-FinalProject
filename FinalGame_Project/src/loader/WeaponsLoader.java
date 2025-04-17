package loader;

import model.Weapons;
import model.Ammunition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WeaponsLoader class
 * @author Jose Montejo
 * This class loads weapons and ammunition from items.txt file
 * It provides methods to access weapons and ammunition by name or ID
 */
public class WeaponsLoader {
    private static Map<String, Weapons> weaponsMap = new HashMap<>();
    private static Map<String, Ammunition> ammoMap = new HashMap<>();

    /**
     * Load weapons and ammunition from items.txt file
     * @author Jose Montejo
     * This method reads the items.txt file and creates Weapons and Ammunition objects
     * @param filePath Path to the items.txt file
     * @return A map containing weapons by name
     */
    public static Map<String, Weapons> loadWeapons(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String type = parts[2];
                    int stat = Integer.parseInt(parts[3]);
                    String description = parts[4];
                    String roomID = parts[5];
                    int quantity = Integer.parseInt(parts[6]);

                    if (type.equals("weapon")) {
                        Weapons weapon = new Weapons(id, name, type, stat, description, roomID, quantity);
                        weaponsMap.put(name, weapon);
                    } else if (type.equals("ammunition")) {
                        // For ammunition, we'll assume rounds = stat
                        Ammunition ammo = new Ammunition(id, name, type, stat, description, roomID, quantity, stat);
                        ammoMap.put(name, ammo);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading weapons from file: " + e.getMessage());
        }

        return weaponsMap;
    }

    /**
     * Get a weapon by its name
     * @param name The name of the weapon
     * @return The Weapons object or null if not found
     */
    public static Weapons getWeaponByName(String name) {
        return weaponsMap.get(name);
    }

    /**
     * Get ammunition by its name
     * @param name The name of the ammunition
     * @return The Ammunition object or null if not found
     */
    public static Ammunition getAmmunitionByName(String name) {
        return ammoMap.get(name);
    }

    /**
     * Get all weapons
     * @return A map of all weapons by name
     */
    public static Map<String, Weapons> getAllWeapons() {
        return weaponsMap;
    }

    /**
     * Get all ammunition
     * @return A map of all ammunition by name
     */
    public static Map<String, Ammunition> getAllAmmunition() {
        return ammoMap;
    }
}
