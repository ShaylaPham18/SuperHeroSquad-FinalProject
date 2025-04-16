public class MonsterFactory {

    // Create all monsters from the game specification
    public static Monster createInfectedDoctor() {
        return new Monster(
                "Infected Doctor",
                "A former surgeon turned monstrous, wielding surgical tools.",
                70,
                10,
                new String[]{"Glock 30", "Shotgun"},
                new int[]{50, 100},
                10,
                "Operating Room",
                null
        );
    }

    public static Monster createRabidNurse() {
        return new Monster(
                "Rabid Nurse",
                "A nurse with a twisted smile, quick and deadly.",
                80,
                10,
                new String[]{"Glock 30", "Shotgun"},
                new int[]{50, 100},
                100,
                "Cafeteria",
                null
        );
    }

    public static Monster createMutatedOrderly() {
        return new Monster(
                "Mutated Orderly",
                "A hulking figure with enhanced strength.",
                60,
                20,
                new String[]{"Glock 30", "Shotgun"},
                new int[]{50, 100},
                50,
                "Lab",
                null
        );
    }

    public static Monster createTormentedPatient() {
        return new Monster(
                "Tormented Patient",
                "A suffering soul now haunting the halls.",
                60,
                20,
                new String[]{"Axe", "Glock 30", "Shotgun"},
                new int[]{25, 50, 100},
                80,
                "Radiology",
                null
        );
    }

    public static Monster createFacehugger() {
        return new Monster(
                "Facehugger",
                "A parasitic creature that seems to latch onto any living organism.",
                30,
                10, // Plus 2 extra damage per action
                new String[]{"Knife"},
                new int[]{50},
                100,
                "ICU",
                "deals 2 extra damage per action"
        );
    }

    public static Monster createAmalgamation() {
        return new Monster(
                "Amalgamation",
                "A zombie that grew by cannibalizing its kind.",
                150,
                25,  // Base damage is 25
                new String[]{"Shotgun", "Flamethrower"},
                new int[]{50, 100},  // Changed from 200 to 100 for flamethrower
                40,
                "Boiler Room",
                "Has a 25% chance to deal 2x critical damage (50 damage)"
        );
    }

    public static Monster createSpitter() {
        return new Monster(
                "Spitter",
                "A zombie that carries an infection.",
                70,
                50, // Plus 5 extra damage per action
                new String[]{"Glock 30", "Shotgun"},
                new int[]{50, 100},
                100,
                "Experiment",
                "Must be defeated to rescue pilot for helicopter ending"
        );
    }

    public static Monster createAbandonedBaby() {
        return new Monster(
                "Abandoned Baby",
                "A child who has not survived.",
                40,
                10,
                new String[]{"Glock 30", "Knife"},
                new int[]{100, 25},
                20,
                "Pediatrics",
                null
        );
    }

    public static Monster createZombieDog() {
        return new Monster(
                "Zombie Dog",
                "A dog that has devoured another zombie.",
                40,
                10,
                new String[]{"Knife", "Axe"},
                new int[]{25, 75},
                60,
                "Waiting Area",
                "Chance of dodging"
        );
    }
}