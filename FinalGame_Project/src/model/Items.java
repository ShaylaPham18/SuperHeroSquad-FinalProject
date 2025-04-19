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
    public String itemNameShortCut;

    //Shayla
    public Items(int id, String name, String type, int stat, String description, String roomID, int quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.stat = stat;
        this.description = description;
        this.roomID = roomID;
        this.quantity = quantity;

    }

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

    public String getItemNameShortCut() {
        return itemNameShortCut;
    }

    public void setItemNameShortCut(String itemNameShortCut) {
        this.itemNameShortCut = itemNameShortCut;
    }
}
