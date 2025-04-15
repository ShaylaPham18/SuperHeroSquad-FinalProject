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
    Map<String, Room> roomMap=new HashMap<>();
    private static Scanner scanner=new Scanner(System.in);

    public void readRooms(){
        Map<String, String> roomExits = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("FinalGame_Project/src/roomtxt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                String roomId = parts[0].trim();
                String roomName = parts[1].trim();
                String roomDescription = parts[2].trim();

                Room room = new Room(roomId, roomName, roomDescription);
                roomMap.put(roomId, room);
                System.out.println(room); //Delete later keep now for testing

                if (parts.length > 3) {
                    roomExits.put(roomId, parts[3].trim());
                }
            }
            bufferedReader.close();

            for (Map.Entry<String, String> entry : roomExits.entrySet()) {
                String roomID2 = entry.getKey();
                String[] exitSplit = entry.getValue().split("\\|");

                Room currentRoom = roomMap.get(roomID2);
                for (String exit : exitSplit) {
                    String[] exitSplitParts = exit.split("->");
                    if (exitSplitParts.length == 2) {
                        String exitDirection = exitSplitParts[0].trim().toUpperCase();
                        String roomGoingTo = exitSplitParts[1].trim();
                        Room exitRoom = roomMap.get(roomGoingTo);
                        if (exitRoom != null) {
                            currentRoom.setExits(exitDirection, exitRoom);
                        } else {
                            System.err.println("Warning: Exit room " + roomGoingTo + " not found for " + roomID2);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void helpMenu(){
        Frame frame=new Frame();
        frame.helpMenu();
    }
}
