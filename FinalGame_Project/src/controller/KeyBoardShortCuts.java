package controller;

import java.util.HashMap;
import java.util.Map;

public class KeyBoardShortCuts {
    private final Map<String, String> shortCuts;

    public KeyBoardShortCuts() {
        shortCuts = new HashMap<>();
        shortCuts.put("N", "NORTH");
        shortCuts.put("S", "SOUTH");
        shortCuts.put("E", "EAST");
        shortCuts.put("W", "WEST");
    }

    public String resolveShortcut(String input) {
        return shortCuts.getOrDefault(input.toUpperCase(), input.toUpperCase());
    }
}
