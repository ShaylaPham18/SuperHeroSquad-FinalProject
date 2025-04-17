package model;

//Shayla
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
     * @param id of item
     * @param name of the item
     * @param description of the item
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
