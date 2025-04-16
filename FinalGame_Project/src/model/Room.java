package model;

import java.util.*;

public class Room {
    private String roomID;
    private String roomName;
    private String roomDescription;
    private boolean roomIsLocked;
    private boolean roomHasStairs;
    private boolean roomIsElevator;
    Map<String,Room> exits;
    List<Items> roomInventory;
    private Puzzle puzzle; //Razan
    public Room(String roomID, String roomName, String roomDescription){
        this.roomID=roomID;
        this.roomName=roomName;
        this.roomDescription=roomDescription;
        this.roomIsLocked=false;
        this.roomHasStairs=false;
        this.roomIsElevator=false;
        this.exits=new HashMap<>();
        this.roomInventory=new ArrayList<>();
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

    public boolean isRoomIsLocked() {
        return roomIsLocked;
    }

    public void setRoomIsLocked(boolean roomIsLocked) {
        this.roomIsLocked = roomIsLocked;
    }

    public boolean isRoomHasStairs() {
        return roomHasStairs;
    }

    public void setRoomHasStairs(boolean roomHasStairs) {
        this.roomHasStairs = roomHasStairs;
    }

    public boolean isRoomIsElevator() {
        return roomIsElevator;
    }

    public void setRoomIsElevator(boolean roomIsElevator) {
        this.roomIsElevator = roomIsElevator;
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

    public List<Items> getRoomInventory() {
        return roomInventory;
    }

    public void setRoomInventory(List<Items> roomInventory) {
        this.roomInventory = roomInventory;
    }

    @Override
    public String toString() {
        return roomName +" -> "+roomDescription;
    }
}
