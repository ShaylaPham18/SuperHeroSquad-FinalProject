package model;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String roomID;
    private String roomName;
    private String roomDescription;
    private boolean roomIsLocked;
    private boolean roomHasStairs;
    private boolean roomIsElevator;
    Map<String,Room> exits;
    public Room(String roomID, String roomName, String roomDescription){
        this.roomID=roomID;
        this.roomName=roomName;
        this.roomDescription=roomDescription;
        this.roomIsLocked=false;
        this.roomHasStairs=false;
        this.roomIsElevator=false;
        this.exits=new HashMap<>();
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

    public void setExits(Map<String, Room> exits) {
        this.exits = exits;
    }

    public Map<String, Room> getExits() {
        return exits;
    }

    @Override
    public String toString() {
        return roomName +" -> "+roomDescription;
    }
}
