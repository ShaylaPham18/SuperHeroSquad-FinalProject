package loader;

import model.Puzzle;
/**
 *In this file we should add all the loader methods of the files to read the files
 **/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class FileLoader {
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
    }
