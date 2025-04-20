package model;

import java.util.Arrays;

/**
 * Razan Abdalla
 * Name|Description|RoomLocation|CorrectAnswer|ResultWhenSolved|MaxAttempts|Hint|RequiredItem
 */
public class Puzzle {
    private String name;
    private String description;
    private String roomLocation;
    private String resultWhenSolved;
    private int maxAttempts;
    private int currentAttempts;
    private String hint;
    private boolean solved;
    private String hintItem;
    private String requiredItem;
    private String[] correctAnswerParts;
    private boolean firstPartEntered = false;

    public Puzzle(String name, String description, String roomLocation,
                  String correctAnswer, String resultWhenSolved,
                  int maxAttempts, String hint) {
        this.name = name;
        this.description = description;
        this.roomLocation = roomLocation;
        this.resultWhenSolved = resultWhenSolved;
        this.maxAttempts = maxAttempts;
        this.currentAttempts = 0;
        this.hint = hint;
        this.solved = false;
        setCorrectAnswer(correctAnswer);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public String getRoomLocation() { return roomLocation; }
    public String getResultWhenSolved() { return resultWhenSolved; }
    public int getCurrentAttempts() { return currentAttempts; }
    public String getHint() { return hint; }
    public boolean isSolved() { return solved; }
    public void setSolved(boolean solved) { this.solved = solved; }
    public String getHintItem() { return hintItem; }
    public String getRequiredItem() {
        return requiredItem;
    }
    public void setRequiredItem(String requiredItem) {
        this.requiredItem = requiredItem;
    }

    public int getMaxAttempts() { return maxAttempts; }
    public String[] getCorrectAnswerParts() { return correctAnswerParts; }
    public boolean isFirstPartEntered() { return firstPartEntered; }
    /**
     * Processes a player's input to solve the puzzle.
     * Supports single-answer and two-part puzzles.
     * Tracks attempts and updates puzzle state.
     *
     * @param input The player's input answer
     * @return true if the input solves the puzzle, false otherwise
     */
    public boolean attempt(String input) {
        String normalizedInput = input.replaceAll("\\s+", "");

        if (correctAnswerParts.length == 1) {
            if (normalizedInput.equalsIgnoreCase(correctAnswerParts[0])) {
                solved = true;
                return true;
            } else {
                currentAttempts++; //
                return false;
            }
        }

        if (correctAnswerParts.length == 2) {
            if (!firstPartEntered) {
                if (normalizedInput.equalsIgnoreCase(correctAnswerParts[0])) {
                    firstPartEntered = true;
                    return false;
                } else {
                    currentAttempts++;
                    return false;
                }
            } else {
                if (normalizedInput.equalsIgnoreCase(correctAnswerParts[1])) {
                    solved = true;
                    return true;
                } else {
                    currentAttempts++;
                    return false;
                }
            }
        }
        return false;
    }
    /**
     * Parses the raw answer string into one or two parts.
     * If the answer contains ';', it splits into a two-part puzzle.
     *
     * @param correctAnswerRaw The raw answer from the puzzle file
     */
    public void setCorrectAnswer(String correctAnswerRaw) {
        if (correctAnswerRaw.contains(";")) {
            this.correctAnswerParts = correctAnswerRaw.split(";");
        } else {
            this.correctAnswerParts = new String[]{correctAnswerRaw};
        }
    }
    /**
     * Checks whether the player has failed enough attempts to receive a hint.
     *
     * @return true if 3 or more failed attempts occurred and the puzzle is not yet solved
     */
    public boolean canGetHint() {
        return currentAttempts >= 3 && !solved;
    }
}
