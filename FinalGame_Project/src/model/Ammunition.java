package model;

public class Ammunition extends Items {
    private int rounds;

    public Ammunition(int id, String name, String type, int stat, String description, String roomID, int quantity, int rounds) {
        super(id, name, type, stat, description, roomID, quantity);
        this.rounds = stat;
    }

    public int getRounds() {
        return rounds;
    }

    public void useAmmo(int amount) {
        rounds -= amount;
    }
}

