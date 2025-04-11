package model;

/**
 * Razan Abdalla
 * //Puzzle File Format:
 * //PuzzleName|Description|RoomLocation|CorrectAnswer|ResultWhenSolved|maxAttempts|Hint
 * // {this is for the puzzle file} \\how i wrote it\\ i may change it later
 */
//Puzzle File Format:
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

    //
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

    // added getter and setter -- I will delete the extra later
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getRoomLocation() {return roomLocation;}
    public void setRoomLocation(String roomLocation) {this.roomLocation = roomLocation;}
    public String getCorrectAnswer() {return correctAnswer;}
    public void setCorrectAnswer(String correctAnswer) {this.correctAnswer = correctAnswer;}
    public String getResultWhenSolved() {return resultWhenSolved;}
    public void setResultWhenSolved(String resultWhenSolved) {this.resultWhenSolved = resultWhenSolved;}
    public int getMaxAttempts() {return maxAttempts;}
    public void setMaxAttempts(int maxAttempts) {this.maxAttempts = maxAttempts;}
    public int getCurrentAttempts() {return currentAttempts;}
    public void setCurrentAttempts(int currentAttempts) {this.currentAttempts = currentAttempts;}
    public String getHint() {return hint;}
    public void setHint(String hint) {this.hint = hint;}
    public boolean isSolved() {return solved;}
    public void setSolved(boolean solved) {this.solved = solved;}

    // added attempt method to track the attempt the player has, marks puzzle as solved if correct
    public boolean attempt(String input) {
        currentAttempts++;
        if (input.equalsIgnoreCase(correctAnswer)) {
            solved = true;
            return true;
        }
        return false;
    }

    //check if the hint is  available to the player.
    // added hint method to be available -->
    // Hint is available if 3 or more attempts have been made and the puzzle is not yet solved.
    public boolean canGetHint() {
        return currentAttempts >= 3 && !solved;
    }
}
