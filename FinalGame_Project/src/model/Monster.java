package model;

/**
 * Jose Montejo
 * Monster class
 * Represents a monster entity in the game with properties like health, damage,
 * and special abilities. Handles combat mechanics including attacks, damage calculation,
 * and special rules for different monster types.
 */
public class Monster {
    private final String name;
    private final String description;
    private int health;
    private final int maxHealth;
    private final int damageToPlayer;
    private final String[] weaponWeaknesses;
    private final int[] weaponDamages;
    private final int spawnChance;
    private final String spawnLocation;
    private final String specialRule;
    private boolean isDefeated;

    /**
     * Jose Montejo
     * Monster Constructor
     * Creates a new monster with specified attributes including name, health,
     * damage capabilities, weapon weaknesses, and special rules.
     */
    public Monster(String name, String description, int health, int damageToPlayer,
                   String[] weaponWeaknesses, int[] weaponDamages, int spawnChance,
                   String spawnLocation, String specialRule) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.maxHealth = health;
        this.damageToPlayer = damageToPlayer;
        this.weaponWeaknesses = weaponWeaknesses;
        this.weaponDamages = weaponDamages;
        this.spawnChance = spawnChance;
        this.spawnLocation = spawnLocation;
        this.specialRule = specialRule;
        this.isDefeated = false;
    }

    // Getters and setters
    /**
     * Jose Montejo
     * getName
     * Returns the monster's name
     */
    public String getName() {
        return name;
    }

    /**
     * Jose Montejo
     * getDescription
     * Returns the monster's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Jose Montejo
     * getHealth
     * Returns the monster's current health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Jose Montejo
     * getMaxHealth
     * Returns the monster's maximum health
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Jose Montejo
     * getDamageToPlayer
     * Returns the base damage this monster deals to the player
     */
    public int getDamageToPlayer() {
        return damageToPlayer;
    }

    /**
     * Jose Montejo
     * getWeaponWeaknesses
     * Returns an array of weapon names that this monster is weak against
     */
    public String[] getWeaponWeaknesses() {
        return weaponWeaknesses;
    }

    /**
     * Jose Montejo
     * getWeaponDamages
     * Returns an array of damage multipliers corresponding to each weakness
     */
    public int[] getWeaponDamages() {
        return weaponDamages;
    }

    /**
     * Jose Montejo
     * getSpawnChance
     * Returns the percentage chance of this monster spawning in its location
     */
    public int getSpawnChance() {
        return spawnChance;
    }

    /**
     * Jose Montejo
     * getSpawnLocation
     * Returns the room ID where this monster can spawn
     */
    public String getSpawnLocation() {
        return spawnLocation;
    }

    /**
     * Jose Montejo
     * getSpecialRule
     * Returns the special rule description for this monster, if any
     */
    public String getSpecialRule() {
        return specialRule;
    }

    /**
     * Jose Montejo
     * isDefeated
     * Returns whether this monster has been defeated (health <= 0)
     */
    public boolean isDefeated() {
        return isDefeated;
    }

    /**
     * Jose Montejo
     * setDefeated
     * Sets the defeated status of this monster
     */
    public void setDefeated(boolean defeated) {
        isDefeated = defeated;
    }

    /**
     * Jose Montejo
     * attack
     * Calculates and returns the damage dealt by this monster to the player.
     * Implements special rules like Amalgamation's critical hits and
     * Facehugger's extra damage.
     */
    public int attack() {
        // For Amalgamation, implement critical hit chance
        if (name.equals("Amalgamation")) {
            // Generate random number between 0 and 100
            int roll = (int) (Math.random() * 100);

            // 25% chance for critical hit
            if (roll < 25) {
                return damageToPlayer * 2; // Critical hit
            }
        }

        // Special rule for Facehugger - extra damage per action
        if (name.equals("Facehugger")) {
            return damageToPlayer + 2; // Base damage plus 2 extra for monster's action
        }

        // Jose Montejo: Added special rule for Spitter - deals 5 extra damage per action
        if (name.equals("Spitter")) {
            return damageToPlayer + 5; // Base damage plus 5 extra for infection damage
        }

        // Basic attack returns the monster's damage value
        return damageToPlayer;
    }

    /**
     * Jose Montejo
     * takeDamage
     * Applies damage to the monster based on the weapon used and base damage.
     * Handles special rules like Zombie Dog's dodge chance and weapon weakness
     * multipliers. Returns true if the monster is defeated after taking damage.
     */
    public boolean takeDamage(String weapon, int baseDamage) {
        // Special rule for Zombie Dog - chance to dodge
        if (name.equals("Zombie Dog")) {
            // 30% chance to dodge
            if (Math.random() < 0.3) {
                return false; // Return false to indicate dodge (no damage)
            }
        }

        double damageMultiplier = 1.0; // Default multiplier (no weakness)

        // Check if the weapon is a weakness
        for (int i = 0; i < weaponWeaknesses.length; i++) {
            if (weaponWeaknesses[i].equalsIgnoreCase(weapon)) {
                // Apply the corresponding damage multiplier
                damageMultiplier = 1.0 + (weaponDamages[i] / 100.0);
                break;
            }
        }

        // Apply damage - use Math.round to properly round the damage
        health -= baseDamage;

        // Ensure health doesn't go below 0
        if (health <= 0) {
            health = 0;
            isDefeated = true;
        }

        // Return true to indicate hit (not dodge)
        return true;
    }

    /**
     * Jose Montejo
     * shouldSpawn
     * Determines if this monster should spawn based on its spawn chance.
     * Returns true if the monster spawns, false otherwise.
     */
    public boolean shouldSpawn() {
        return Math.random() * 100 < spawnChance;
    }

    /**
     * Jose Montejo
     * reset
     * Resets the monster's health to its maximum value and clears its
     * defeated status. Used when respawning monsters.
     */
    public void reset() {
        health = maxHealth;
        isDefeated = false;
    }

    /**
     * Jose Montejo
     * toString
     * Returns a string representation of the monster, showing its name
     * and current/maximum health.
     */
    @Override
    public String toString() {
        return name + " (Health: " + health + "/" + maxHealth + ")";
    }
}