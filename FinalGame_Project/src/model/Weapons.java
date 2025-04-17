package model;

public class Weapons extends Items{
    private int attackDmg;

    /**
     * Constructor
     *
     * @param id          of item
     * @param name        of the item
     * @param type
     * @param stat
     * @param description of the item
     * @param roomID
     * @param quantity
     */
    public Weapons(int id, String name, String type, int stat, String description, String roomID, int quantity) {
        super(id, name, type, stat, description, roomID, quantity);
        this.attackDmg = stat;
    }

    public int getAttackDmg() {
        return attackDmg;
    }
}
