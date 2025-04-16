import java.util.Scanner;

public class MonsterTest {
    private static Scanner scanner = new Scanner(System.in);
    private static int playerHealth = 100;
    private static boolean hasGlock = false;
    private static boolean hasShotgun = false;
    private static boolean hasKnife = false;
    private static boolean hasAxe = false;
    private static boolean hasFlamethrower = false;

    public static void main(String[] args) {
        System.out.println("Monster Feature Test Environment");
        System.out.println("===============================");

        // Main menu
        boolean running = true;
        while (running) {
            System.out.println("\nWhat would you like to test?");
            System.out.println("1. Test all monsters in combat");
            System.out.println("2. Test specific monster");
            System.out.println("3. Test monster spawning");
            System.out.println("4. Equip/unequip weapons");
            System.out.println("5. Exit");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    testAllMonsters();
                    break;
                case 2:
                    testSpecificMonster();
                    break;
                case 3:
                    testMonsterSpawning();
                    break;
                case 4:
                    manageWeapons();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        System.out.println("Test complete!");
    }

    private static void testAllMonsters() {
        // Reset player health
        playerHealth = 100;

        // Array of all monsters to test
        Monster[] monsters = {
                MonsterFactory.createInfectedDoctor(),
                MonsterFactory.createRabidNurse(),
                MonsterFactory.createMutatedOrderly(),
                MonsterFactory.createTormentedPatient(),
                MonsterFactory.createFacehugger(),
                MonsterFactory.createAmalgamation(),
                MonsterFactory.createSpitter(),
                MonsterFactory.createAbandonedBaby(),
                MonsterFactory.createZombieDog()
        };

        // Test each monster in combat with option to exit
        for (int i = 0; i < monsters.length; i++) {
            System.out.println("\nTesting monster " + (i+1) + " of " + monsters.length + ": " + monsters[i].getName());
            System.out.println("Press Enter to continue or type 'exit' to return to main menu:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Returning to main menu...");
                return;
            }

            testMonsterCombat(monsters[i]);

            // After each monster combat, give option to continue or exit
            if (i < monsters.length - 1) {
                System.out.println("\nPress Enter to test next monster or type 'exit' to return to main menu:");
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Returning to main menu...");
                    return;
                }
            }
        }

        System.out.println("\nAll monsters tested. Returning to main menu...");
    }

    private static void testSpecificMonster() {
        System.out.println("\nSelect a monster to test:");
        System.out.println("1. Infected Doctor");
        System.out.println("2. Rabid Nurse");
        System.out.println("3. Mutated Orderly");
        System.out.println("4. Tormented Patient");
        System.out.println("5. Facehugger");
        System.out.println("6. Amalgamation");
        System.out.println("7. Spitter");
        System.out.println("8. Abandoned Baby");
        System.out.println("9. Zombie Dog");

        int choice = getIntInput();
        Monster monster = null;

        switch (choice) {
            case 1:
                monster = MonsterFactory.createInfectedDoctor();
                break;
            case 2:
                monster = MonsterFactory.createRabidNurse();
                break;
            case 3:
                monster = MonsterFactory.createMutatedOrderly();
                break;
            case 4:
                monster = MonsterFactory.createTormentedPatient();
                break;
            case 5:
                monster = MonsterFactory.createFacehugger();
                break;
            case 6:
                monster = MonsterFactory.createAmalgamation();
                break;
            case 7:
                monster = MonsterFactory.createSpitter();
                break;
            case 8:
                monster = MonsterFactory.createAbandonedBaby();
                break;
            case 9:
                monster = MonsterFactory.createZombieDog();
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        // Reset player health
        playerHealth = 100;

        // Test the selected monster
        testMonsterCombat(monster);
    }

    private static void testMonsterCombat(Monster monster) {
        System.out.println("\n=== Testing combat with " + monster.getName() + " ===");
        System.out.println(monster.getDescription());
        System.out.println("Health: " + monster.getHealth());
        System.out.println("Damage: " + monster.getDamageToPlayer());

        if (monster.getSpecialRule() != null) {
            System.out.println("Special Rule: " + monster.getSpecialRule());
        }

        System.out.println("\nWeapon Weaknesses:");
        String[] weaknesses = monster.getWeaponWeaknesses();
        int[] damages = monster.getWeaponDamages();

        for (int i = 0; i < weaknesses.length; i++) {
            System.out.println("- " + weaknesses[i] + ": " + damages[i] + "% damage bonus");
        }

        // Combat loop
        boolean inCombat = true;
        while (inCombat && playerHealth > 0 && monster.getHealth() > 0) {
            System.out.println("\nYour health: " + playerHealth);
            System.out.println("Monster health: " + monster.getHealth());
            // Inside the combat loop in testMonsterCombat method
            System.out.println("\nWhat would you like to do?");

            int option = 1;
            System.out.println(option++ + ". Attack with bare hands");

            if (hasGlock) {
                System.out.println(option++ + ". Attack with Glock 30");
            }

            if (hasShotgun) {
                System.out.println(option++ + ". Attack with Shotgun");
            }

            if (hasKnife) {
                System.out.println(option++ + ". Attack with Knife");
            }

            if (hasAxe) {
                System.out.println(option++ + ". Attack with Axe");
            }

            if (hasFlamethrower) {
                System.out.println(option++ + ". Attack with Flamethrower");
            }

// Add healing options
            System.out.println(option++ + ". Use First Aid Kit (Restore 10% health)");
            System.out.println(option++ + ". Use Painkiller A (Restore 25% health)");
            System.out.println(option++ + ". Use Painkiller B (Restore 50% health)");
            System.out.println(option++ + ". Use Vaccine (Restore 100% health)");

            System.out.println(option + ". Flee");

            int choice = getIntInput();
            option = 1;

            if (choice == option++) {
                // Bare hands
                attackMonster(monster, "Bare hands", 2);
            } else if (hasGlock && choice == option++) {
                // Glock
                attackMonster(monster, "Glock 30", 8);
            } else if (hasShotgun && choice == option++) {
                // Shotgun
                attackMonster(monster, "Shotgun", 15);
            } else if (hasKnife && choice == option++) {
                // Knife
                attackMonster(monster, "Knife", 5);
            } else if (hasAxe && choice == option++) {
                // Axe
                attackMonster(monster, "Axe", 7);
            } else if (hasFlamethrower && choice == option++) {
                // Flamethrower
                attackMonster(monster, "Flamethrower", 20);
            }
// Handle healing options
            else if (choice == option++) {
                // Use First Aid Kit
                useHealingItem(monster, "First Aid Kit", 10);
            } else if (choice == option++) {
                // Use Painkiller A
                useHealingItem(monster, "Painkiller A", 25);
            } else if (choice == option++) {
                // Use Painkiller B
                useHealingItem(monster, "Painkiller B", 50);
            } else if (choice == option++) {
                // Use Vaccine
                useHealingItem(monster, "Vaccine", 100);
            } else if (choice == option) {
                // Flee
                System.out.println("You flee from the monster!");
                inCombat = false;
            } else {
                System.out.println("Invalid choice!");
                continue;
            }

            // Monster attacks if still alive and player hasn't fled
            if (inCombat && monster.getHealth() > 0) {
                int damage = monster.attack();
                boolean criticalHitDisplayed = false;

                // Check if this was an Amalgamation critical hit (critical hits are double damage)
                if (monster.getName().equals("Amalgamation") && damage == monster.getDamageToPlayer() * 2) {
                    // Critical hit message was already displayed in the monster.attack() method
                    criticalHitDisplayed = true;
                }

                // Apply special rules
                if (monster.getName().equals("Spitter")) {
                    damage += 5; // Extra damage per action
                    System.out.println("The " + monster.getName() + " attacks you for " + monster.getDamageToPlayer() + " damage!");
                    System.out.println("Special Rule Activates: The " + monster.getName() + " deals 5 additional damage!");
                } else if (monster.getName().equals("Facehugger")) {
                    System.out.println("The " + monster.getName() + " attacks you for " + monster.getDamageToPlayer() + " damage!");
                    System.out.println("Special Rule Activates: The " + monster.getName() + " deals 2 additional damage!");
                } else if (!criticalHitDisplayed) {
                    // Only display the regular attack message if a critical hit message wasn't already displayed
                    System.out.println("The " + monster.getName() + " attacks you for " + damage + " damage!");
                }

                playerHealth -= damage;

                if (playerHealth <= 0) {
                    System.out.println("You have been defeated!");
                }
            }
        }

        // Reset monster for future tests
        monster.reset();
    }

    private static void useHealingItem(Monster monster, String itemName, int healAmount) {
        // Calculate healing amount (percentage of max health)
        int maxHealth = 100; // Assuming max health is 100
        int actualHeal = healAmount;
        // Apply healing
        int oldHealth = playerHealth;
        playerHealth = Math.min(maxHealth, playerHealth + actualHeal);
        int healedAmount = playerHealth - oldHealth;

        System.out.println("You used " + itemName + " and restored " + healedAmount + " health!");
        System.out.println("Your health: " + playerHealth);

        // Apply Facehugger's special rule - deals 2 extra damage per action
        if (monster.getName().equals("Facehugger")) {
            System.out.println("Special Rule Activates: The Facehugger deals 2 damage as you use an item!");
            playerHealth -= 2;
            System.out.println("Your health after Facehugger damage: " + playerHealth);
        }
    }

    private static void attackMonster(Monster monster, String weapon, int baseDamage) {
        System.out.println("You attack with " + weapon + "!");

        // Store the monster's health before the attack
        int healthBefore = monster.getHealth();

        // Apply damage - this will also handle dodge logic for Zombie Dog
        boolean defeated = monster.takeDamage(weapon, baseDamage);

        // If the health didn't change, it means the attack was dodged (for Zombie Dog)
        if (monster.getHealth() == healthBefore && monster.getName().equals("Zombie Dog")) {
            // The dodge message is already printed in the takeDamage method
            // Don't print damage message or health remaining
            return;
        }

        // Calculate actual damage dealt by comparing health before and after
        int actualDamage = healthBefore - monster.getHealth();

        // Display the actual damage dealt
        System.out.println("You dealt " + actualDamage + " damage to the " + monster.getName() + "!");

        if (defeated) {
            System.out.println("You defeated the " + monster.getName() + "!");
        } else {
            System.out.println("The " + monster.getName() + " has " + monster.getHealth() + " health remaining.");
        }

        // Apply Facehugger's special rule damage to player during player's action
        if (monster.getName().equals("Facehugger")) {
            playerHealth -= 2;
        }
    }

    // Add this helper method to calculate damage (same logic as in Monster.takeDamage)
    private static int calculateDamage(Monster monster, String weapon, int baseDamage) {
        double damageMultiplier = 1.0;
        String[] weaknesses = monster.getWeaponWeaknesses();
        int[] damages = monster.getWeaponDamages();

        // Check if the weapon is a weakness
        for (int i = 0; i < weaknesses.length; i++) {
            if (weaknesses[i].equals(weapon)) {
                // Apply the corresponding damage multiplier
                damageMultiplier = damages[i] / 100.0;
                break;
            }
        }

        // Calculate and return the actual damage
        return (int)(baseDamage * damageMultiplier);
    }

    private static void testMonsterSpawning() {
        System.out.println("\n=== Testing Monster Spawning ===");
        System.out.println("Running 10 spawn tests for each monster...");

        Monster[] monsters = {
                MonsterFactory.createInfectedDoctor(),
                MonsterFactory.createRabidNurse(),
                MonsterFactory.createMutatedOrderly(),
                MonsterFactory.createTormentedPatient(),
                MonsterFactory.createFacehugger(),
                MonsterFactory.createAmalgamation(),
                MonsterFactory.createSpitter(),
                MonsterFactory.createAbandonedBaby(),
                MonsterFactory.createZombieDog()
        };

        for (Monster monster : monsters) {
            int spawnCount = 0;
            for (int i = 0; i < 10; i++) {
                if (monster.shouldSpawn()) {
                    spawnCount++;
                }
            }

            System.out.println(monster.getName() + " (Chance: " + monster.getSpawnChance() +
                    "%): Spawned " + spawnCount + "/10 times");
        }
    }

    private static void manageWeapons() {
        System.out.println("\n=== Manage Weapons ===");
        System.out.println("1. " + (hasGlock ? "Unequip" : "Equip") + " Glock 30");
        System.out.println("2. " + (hasShotgun ? "Unequip" : "Equip") + " Shotgun");
        System.out.println("3. " + (hasKnife ? "Unequip" : "Equip") + " Knife");
        System.out.println("4. " + (hasAxe ? "Unequip" : "Equip") + " Axe");
        System.out.println("5. " + (hasFlamethrower ? "Unequip" : "Equip") + " Flamethrower");
        System.out.println("6. Back");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                hasGlock = !hasGlock;
                System.out.println("Glock 30 " + (hasGlock ? "equipped" : "unequipped"));
                break;
            case 2:
                hasShotgun = !hasShotgun;
                System.out.println("Shotgun " + (hasShotgun ? "equipped" : "unequipped"));
                break;
            case 3:
                hasKnife = !hasKnife;
                System.out.println("Knife " + (hasKnife ? "equipped" : "unequipped"));
                break;
            case 4:
                hasAxe = !hasAxe;
                System.out.println("Axe " + (hasAxe ? "equipped" : "unequipped"));
                break;
            case 5:
                hasFlamethrower = !hasFlamethrower;
                System.out.println("Flamethrower " + (hasFlamethrower ? "equipped" : "unequipped"));
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}