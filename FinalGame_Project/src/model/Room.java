package model;

import java.util.*;

//justin
public class Room {
    private String roomID;
    private String roomName;
    private String roomDescription;
    private boolean roomHasBeenVisited;
    Map<String,Room> exits;
    List<Items> roomInventory; //Shayla - list of the items in the current room
    private Puzzle puzzle; //Razan
    private boolean roomIsLocked;
    private String unlockItem;
    public Room(String roomID, String roomName, String roomDescription){
        this.roomID=roomID;
        this.roomName=roomName;
        this.roomDescription=roomDescription;
        this.roomHasBeenVisited=false;
        this.exits=new HashMap<>();
        this.roomInventory=new ArrayList<>();
        this.roomIsLocked=false;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public void setExits(String direction,Room room) {
        exits.put(direction, room);
    }

    public Room getExits(String direction) {
        return exits.get(direction);
    }
    // Getter for the puzzle //---> Razan
    public Puzzle getPuzzle() {
        return this.puzzle;
    }

    // Setter for the puzzle //---> Razan
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
    public Set<String> getExitDirections() { //RAZAN
        return exits.keySet();
    }

    //Manging of inventory -Shayla
    public List<Items> getRoomInventory() {
        return roomInventory;
    }

    public boolean isRoomHasBeenVisited() {
        return roomHasBeenVisited;
    }

    public void setRoomHasBeenVisited(boolean roomHasBeenVisited) {
        this.roomHasBeenVisited = roomHasBeenVisited;
    }

    public void beenHereBefore(){
        if (roomHasBeenVisited){
            System.out.println("\nHmmm I've been here before");
        }
    }

    public boolean isRoomIsLocked() {
        return roomIsLocked;
    }

    public void setRoomIsLocked(boolean roomIsLocked) {
        this.roomIsLocked = roomIsLocked;
    }

    public void roomIsLocked(){
        if (isRoomIsLocked()){
            System.err.println("room is locked");
        }
    }

    public String getUnlockItem() {
        return unlockItem;
    }

    public void setUnlockItem(String unlockItem) {
        this.unlockItem = unlockItem;
    }

    //// razan test
    // Use this for puzzle-based unlocking
    public void setLocked(boolean locked) {
        this.roomIsLocked = locked;
    }

    // Use this for clearing required item if puzzle unlocks the room
    public void setRequiredItem(String itemName) {
        this.unlockItem = itemName;
    }
    @Override
    public String toString() {
        return roomName +" -> "+roomDescription;
    }
}
