package loader;

import model.Puzzle;
import model.Room;
/**
 *In this file we should add all the loader methods of the files to read the files
 **/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileLoader {
    private static String correctAnswer;

    public static List<Puzzle> loadPuzzles(String filePath) throws IOException {
            List<Puzzle> puzzles = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 7) {
                    String name = parts[0].trim();
                    String description = parts[1].trim();
                    String roomLocation = parts[2].trim();
                    String correctAnswer = parts[3].trim();
                    String resultWhenSolved = parts[4].trim();
                    int maxAttempts = Integer.parseInt(parts[5].trim());
                    String hint = parts[6].trim();

                    Puzzle puzzle = new Puzzle(name, description, roomLocation, correctAnswer, resultWhenSolved, maxAttempts, hint);
                    puzzles.add(puzzle);
                }
            }

            reader.close();
            return puzzles;
        }

    Map<String, Room> roomMap = new HashMap<>();
    //private static Scanner scanner=new Scanner(System.in);
    public Map<String, Room> readRooms(){
        Map<String, String> roomExits = new HashMap<>();
        try {
            //you guys use this 1 PLEASE JUST COMMENT AND UNCOMMENT

            //BufferedReader bufferedReader = new BufferedReader(new FileReader("FinalGame_Project/room.txt"));
            BufferedReader bufferedReader = new BufferedReader(new FileReader("room.txt"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",",4);
                String roomId = parts[0].trim();
                String roomName = parts[1].trim();
                String roomDescription = parts[2].trim();

                Room room = new Room(roomId, roomName, roomDescription);
                roomMap.put(roomId, room);

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
        return roomMap;
    }
}
