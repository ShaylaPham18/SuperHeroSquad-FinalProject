package model;

//Shayla

/**
 * Items Class
 */
public class Items {
    protected int id;
    protected String name;
    protected String type;
    protected int stat;
    protected String description;
    protected String roomID;
    protected int quantity;

    //Shayla

    /**
     * Constructor
     *
     * @param id item id
     * @param name what the item is called
     * @param type what the item is
     * @param stat depends on the item (ex: consumable = healthPercentage to be healed, weapon = damage)
     * @param description of the item
     * @param roomID what room it is located in
     * @param quantity how much of it there is
     */
    public Items(int id, String name, String type, int stat, String description, String roomID, int quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.stat = stat;
        this.description = description;
        this.roomID = roomID;
        this.quantity = quantity;

    }

    /**
     * Temporary placeholder
     *
     * @param number placeholder parameter
     * @param name parameter for item name
     */
    public Items(int number, String name) {
    }

    //Shayla
    /**
     * Getter Methods:
     * getName - Used to retrieve the item's name
     * getDescription - Used to retrieve the item's description
     *
     * @return item name & description
     */

    //Shayla
    public String getName() {
        return name;
    }

    //Shayla
    public String getDescription() {
        return description;
    }
}
