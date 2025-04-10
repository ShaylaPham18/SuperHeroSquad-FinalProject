import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        //Justin
        //added my help menu can edit later just checking if it works
        //WORK IN PROGRESS will be changed  
        Scanner scanner=new Scanner(System.in);
        System.out.println("testing help menu type help: ");
        String a=scanner.nextLine();
        if (a.equalsIgnoreCase("help")){
            helpMenu();
        }
    }

    private Floors currentFloor;
    private Map<Integer,Floors> floorMap;
    private Room currentRoom;
    private Map<Integer,Room> roomMap;

    public void readFloors(){
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("FinalGame_Project/src/floortxt"));
            String lines;
            while ((lines = bufferedReader.readLine()) != null){
            String[] parts=lines.split(",");
            int floorNumber=Integer.parseInt(parts[0]);
            String floorName=parts[1];
            Floors floors=new Floors(floorNumber,floorName);
            floorMap.put(floorNumber,floors);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void helpMenu(){
        Frame frame=new Frame();
        frame.helpMenu();
    }
}
