package controller;

import java.util.HashMap;
import java.util.Map;

public class KeyBoardShortCuts {
    private final Map<String, String> shortCuts;
    private final Map<String,String> itemShortcuts;

    //Justin, Shayla
    public KeyBoardShortCuts() {
        shortCuts = new HashMap<>();
        shortCuts.put("N", "NORTH");
        shortCuts.put("S", "SOUTH");
        shortCuts.put("E", "EAST");
        shortCuts.put("W", "WEST");
        itemShortcuts=new HashMap<>();
        itemShortcuts.put("gas","gas mask (gas)");
        itemShortcuts.put("pka","painkiller a (pka)");
        itemShortcuts.put("pkb","painkiller b (pkb)");
        itemShortcuts.put("fak","first aid kit (fak)");
        itemShortcuts.put("vax","vaccine (vax)");
        itemShortcuts.put("12gs","12 gauge shotgun shells (12gs)");
        itemShortcuts.put("acp",".45 ACP (acp)");
        itemShortcuts.put("glock","glock 30 (glock)");
        itemShortcuts.put("sgun","shotgun (sgun)");
        itemShortcuts.put("hkey","helicopter key (hkey)");
        itemShortcuts.put("eye", "eyeball (eye)");
        itemShortcuts.put("hand","hand/fingerprint (hand)");
        itemShortcuts.put("id","ID badge (id)");
        itemShortcuts.put("fire","fire extinguisher (fire)");
    }

    public String resolveShortcut(String input) {
        return shortCuts.getOrDefault(input.toUpperCase(), input.toUpperCase());
    }

    public String resolveItems(String input){
        return itemShortcuts.getOrDefault(input.toLowerCase(),input);
    }
}
