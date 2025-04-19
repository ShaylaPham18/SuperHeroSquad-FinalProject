package controller;

import java.util.HashMap;
import java.util.Map;

public class KeyBoardShortCuts {
    private final Map<String, String> shortCuts;
    private final Map<String,String> itemShortcuts;

    //Justin, Shayla (added some more)
    public KeyBoardShortCuts() {
        shortCuts = new HashMap<>();
        shortCuts.put("N", "NORTH");
        shortCuts.put("S", "SOUTH");
        shortCuts.put("E", "EAST");
        shortCuts.put("W", "WEST");
        itemShortcuts=new HashMap<>();
        itemShortcuts.put("gas","gas mask");
        itemShortcuts.put("pka","painkiller a");
        itemShortcuts.put("pkb","painkiller b");
        itemShortcuts.put("fak","first aid kit");
        itemShortcuts.put("vax","vaccine");
        itemShortcuts.put("12gs","12 gauge shotgun shells");
        itemShortcuts.put("acp",".45 ACP");
        itemShortcuts.put("glock","glock 30");
        itemShortcuts.put("sgun","shotgun");
        itemShortcuts.put("hkey","helicopter key");
        itemShortcuts.put("eye", "eyeball");
        itemShortcuts.put("hand","hand/fingerprint");
        itemShortcuts.put("id","ID badge");
        itemShortcuts.put("fire","fire extinguisher");
    }

    public String resolveShortcut(String input) {
        return shortCuts.getOrDefault(input.toUpperCase(), input.toUpperCase());
    }

    public String resolveItems(String input){
        return itemShortcuts.getOrDefault(input.toLowerCase(),input);
    }
}
