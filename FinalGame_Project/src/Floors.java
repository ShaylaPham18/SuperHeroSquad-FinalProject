import java.util.HashMap;
import java.util.Map;

public class Floors {
    private int floorNumber;
    private String floorName;
    Map<String,Floors> upFloorExits;
    Map<String,Floors> downFloorExits;
    Floors(int floorNumber,String floorName){
        this.floorNumber=floorNumber;
        this.floorName=floorName;
        this.upFloorExits=new HashMap<>();
        this.downFloorExits=new HashMap<>();
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

    public Floors getUpFloorExits(String direction) {
        return upFloorExits.get(direction);
    }

    public void setUpFloorExits(String direction,Floors floor) {
        upFloorExits.put(direction, floor);
    }
    public Floors getDownFloorExits(String direction) {
        return downFloorExits.get(direction);
    }

    public void setDownFloorExits(String direction,Floors floor) {
        downFloorExits.put(direction, floor);
    }
}
