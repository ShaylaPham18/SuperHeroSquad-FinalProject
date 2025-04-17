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
     * @param description
     */
    public Consumables(int id, String name, int healPercentage, String description) {
        super(id, name, description);
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
