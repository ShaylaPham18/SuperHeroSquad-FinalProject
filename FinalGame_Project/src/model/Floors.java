package model;

import java.util.HashMap;
import java.util.Map;

public class Floors {
    private int floorNumber;
    private String floorName;
    Map<String,Floors> floorExits;
    public Floors(int floorNumber, String floorName){
        this.floorNumber=floorNumber;
        this.floorName=floorName;
        this.floorExits=new HashMap<>();
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Map<String, Floors> getFloorExits() {
        return floorExits;
    }

    public void setFloorExits(Map<String, Floors> floorExits) {
        this.floorExits = floorExits;
    }
}
