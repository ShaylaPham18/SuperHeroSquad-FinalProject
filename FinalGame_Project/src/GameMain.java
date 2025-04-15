import model.Floors;
import model.Room;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        //Justin
        //added my help menu can edit later just checking if it works
        //WORK IN PROGRESS will be changed
        GameMain gameMain=new GameMain();
        /*Scanner scanner=new Scanner(System.in);
        System.out.println("testing help menu type help: ");
        String a=scanner.nextLine();
        if (a.equalsIgnoreCase("help")){
            helpMenu();
        }*/
        gameMain.readRooms();
    }
    Room currentRoom;
    Map<String, Room> roomMap=new HashMap<>();
    private static Scanner scanner=new Scanner(System.in);
    Map<String,String> roomExits=new HashMap<>();

    public void readRooms(){
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("FinalGame_Project/src/roomtxt"));
            String lines;
            while ((lines=bufferedReader.readLine())!=null){
                String[] parts=lines.split(",");
                String roomId=parts[0];
                String roomName=parts[1];
                String roomDescription=parts[2];
                Room room=new Room(roomId,roomName,roomDescription);
                System.out.println(room);
                roomMap.put(roomId,room);
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
