import java.util.HashMap;
import java.util.Map;

public class KeyBoardShortCuts {
    private Map<String,String> keyBoardShortCuts4Player(){
        Map<String,String> shortCuts=new HashMap<>();
        shortCuts.put("N","NORTH");
        shortCuts.put("S","SOUTH");
        shortCuts.put("E","EAST");
        shortCuts.put("W","WEST");
        return shortCuts;
    }
}
