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

    // Constructor with all fields
    public Items(int id, String name, String type, int stat, String description, String roomID, int quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.stat = stat;
        this.description = description;
        this.roomID = roomID;
        this.quantity = quantity;
    }

    // Unused alternate constructor
    public Items(int number, String name) {
        this.id = number;
        this.name = name;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getRoomID() {
        return roomID;
    }

    public int getStat() {
        return stat;
    }

    public int getId() {
        return id;
    }
}
