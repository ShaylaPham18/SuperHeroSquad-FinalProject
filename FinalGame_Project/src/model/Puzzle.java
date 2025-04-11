package model;

/**
 * Razan Abdalla
 */
//PuzzleName|Description|RoomLocation|CorrectAnswer|ResultWhenSolved|maxAttempts|Hint// {this is for the puzzle file} \\how i wrote it\\
public class Puzzle {
    private String name;
    private String description;
    private String roomLocation;
    private String correctAnswer;
    private String resultWhenSolved;
    private int maxAttempts;
    private int currentAttempts;
    private String hint;
    private boolean solved;

    public Puzzle(String name, String description, String roomLocation,
                  String correctAnswer, String resultWhenSolved,
                  int maxAttempts, int currentAttempts, String hint,
                  boolean solved) {
        this.name = name;
        this.description = description;
        this.roomLocation = roomLocation;
        this.correctAnswer = correctAnswer;
        this.resultWhenSolved = resultWhenSolved;
        this.maxAttempts = maxAttempts;
        this.currentAttempts = currentAttempts;
        this.hint = hint;
        this.solved = solved;
    }

}
