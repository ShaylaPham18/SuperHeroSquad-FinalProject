package model;

//Shayla

/**
 * Child class: consumables - Parent class - items
 * Contain attribute: healPercentage (how much the item heals)
 */
public class Consumables extends Items{
    public int healPercentage;

    //Shayla

    /**
     * Constructor
     *
     * @param id
     * @param name
     * @param type
     * @param stat
     * @param description
     * @param roomID
     * @param quantity
     * @param healPercentage
     */
    public Consumables(int id, String name, String type, int stat, String description, String roomID, int quantity, int healPercentage) {
        super(id, name, type, stat, description, roomID, quantity);
        this.healPercentage = healPercentage;
    }

    //Shayla

    /**
     * Calculate how much HP this consumable would restore, based on the player’s current health.
     *
     * @param playerHealthHealed player health before consumption
     * @return HP restored
     */
    public int calculateHealthHealed(int playerHealthHealed){
        return (playerHealthHealed * healPercentage) / 100;
    }
}
