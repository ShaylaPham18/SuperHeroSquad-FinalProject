public class Monster {
    private String name;
    private String description;
    private int health;
    private int maxHealth;
    private int damageToPlayer;
    private String[] weaponWeaknesses;
    private int[] weaponDamages;
    private int spawnChance;
    private String spawnLocation;
    private String specialRule;

    // Constructor
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
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamageToPlayer() {
        return damageToPlayer;
    }

    public String[] getWeaponWeaknesses() {
        return weaponWeaknesses;
    }

    public int[] getWeaponDamages() {
        return weaponDamages;
    }

    public int getSpawnChance() {
        return spawnChance;
    }

    public String getSpawnLocation() {
        return spawnLocation;
    }

    public String getSpecialRule() {
        return specialRule;
    }

    // Combat methods
    public int attack() {
        // For Amalgamation, implement critical hit chance
        if (name.equals("Amalgamation")) {
            // Generate random number between 0 and 100
            int roll = (int)(Math.random() * 100);

            // 25% chance for critical hit
            if (roll < 25) {
                System.out.println("The Amalgamation lands a CRITICAL HIT for " + (damageToPlayer * 2) + " damage!");
                return damageToPlayer * 2;
            }
        }

        // Special rule for Facehugger - extra damage per action
        if (name.equals("Facehugger")) {
            return damageToPlayer + 2; // Base damage plus 2 extra for monster's action
        }

        // Special rules for other monsters can be added here

        // Basic attack returns the monster's damage value
        return damageToPlayer;
    }

    public boolean takeDamage(String weapon, int baseDamage) {
        // Special rule for Zombie Dog - chance to dodge
        if (name.equals("Zombie Dog")) {
            // 30% chance to dodge
            if (Math.random() < 0.3) {
                System.out.println("The Zombie Dog dodges your attack!");
                return false; // Return false to indicate dodge (no damage)
            }
        }

        double damageMultiplier = 1.0; // Default multiplier (no weakness)

        // Check if the weapon is a weakness
        for (int i = 0; i < weaponWeaknesses.length; i++) {
            if (weaponWeaknesses[i].equals(weapon)) {
                // Apply the corresponding damage multiplier
                damageMultiplier = 1.0 + (weaponDamages[i] / 100.0);
                break;
            }
        }

        // Apply damage - use Math.round to properly round the damage
        int actualDamage = (int)Math.round(baseDamage * damageMultiplier);
        health -= actualDamage;

        // Special rule for Facehugger - deals 2 extra damage per action (including player's attack)
        if (name.equals("Facehugger")) {
            System.out.println("Special Rule Activates: The Facehugger deals 2 damage as you attack!");
            return health <= 0;
        }

        // Ensure health doesn't go below 0
        if (health < 0) {
            health = 0;
        }

        // Return true if the monster is defeated
        return health <= 0;
    }

    // Check if monster should spawn based on chance
    public boolean shouldSpawn() {
        return Math.random() * 100 < spawnChance;
    }

    // Reset monster health (for respawning)
    public void reset() {
        health = maxHealth;
    }

    @Override
    public String toString() {
        return name + " (Health: " + health + "/" + maxHealth + ")";
    }
}