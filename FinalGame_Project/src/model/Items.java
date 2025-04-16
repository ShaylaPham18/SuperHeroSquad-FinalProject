package model;

//Shayla
public class Items {
    private int id;
    private String name;
    private String description;

    //Shayla
    /**
     * Constructor
     *
     * @param id of item
     * @param name of the item
     * @param description of the item
     */
    public Items(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
