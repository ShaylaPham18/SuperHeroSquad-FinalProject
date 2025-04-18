package model;

import java.util.List;

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

    public boolean attempt(String input) {
        String normalizedInput = input.replaceAll("\\s+", "");

        // Single-answer puzzle
        if (correctAnswerParts.length == 1) {
            if (normalizedInput.equalsIgnoreCase(correctAnswerParts[0])) {
                solved = true;
                return true;
            } else {
                currentAttempts++; //
                return false;
            }
        }

        // Two-part answer puzzle
        if (correctAnswerParts.length == 2) {
            if (!firstPartEntered) {
                if (normalizedInput.equalsIgnoreCase(correctAnswerParts[0])) {
                    firstPartEntered = true;
                    System.out.println("✔ First part correct. Now pull the panel lever (use panel lever) or enter the next code.");
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



    public void setCorrectAnswer(String correctAnswerRaw) {
        if (correctAnswerRaw.contains(";")) {
            this.correctAnswerParts = correctAnswerRaw.split(";");
        } else {
            this.correctAnswerParts = new String[]{correctAnswerRaw};
        }
    }

    public boolean canGetHint() {
        return currentAttempts >= 3 && !solved;
    }
}
