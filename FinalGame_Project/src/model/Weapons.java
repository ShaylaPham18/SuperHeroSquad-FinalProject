package model;

//Shayla

/**
 * Child class: weapons - Parent class: items
 * Contains attribute attackDmg (damage the weapon does)
 */
public class Weapons extends Items{
    private int attackDmg;

    //Shayla
    /**
     * Constructor
     *
     * @param id of the weapon
     * @param name weapon
     * @param type it is a weapon
     * @param stat attack damage
     * @param description of the item
     * @param roomID id of the room
     * @param quantity how many there is in a room
     */
    public Weapons(int id, String name, String type, int stat, String description, String roomID, int quantity) {
        super(id, name, type, stat, description, roomID, quantity);
        this.attackDmg = stat;
    }

    //Shayla
    /**
     * Getter for weapon attack damage
     *
     * @return damage the weapon deals
     */
    public int getAttackDmg() {
        return attackDmg;
    }
}
