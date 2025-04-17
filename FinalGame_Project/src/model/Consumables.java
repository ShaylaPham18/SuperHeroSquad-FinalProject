package model;

//Shayla
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
    public int getHealPercentage() {
        return healPercentage;
    }

    //Shayla
    public int calculateHealthHealed(int playerHealthHealed){
        return (playerHealthHealed * healPercentage) / 100;
    }
}
