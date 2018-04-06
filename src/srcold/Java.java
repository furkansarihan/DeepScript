package srcold;

import java.awt.Color;
import java.util.HashMap;

public class Java {
    HashMap keywords = new HashMap<String, Color>();
    Java() {
        Init();
    }
    public void Init() {
        keywords.put(" int", new Color(255, 102, 102));
        keywords.put(" for", new Color(255, 102, 102));
        keywords.put(" import", new Color(255, 102, 102));
        keywords.put(" for", new Color(255, 102, 102));
        keywords.put(" while", new Color(255, 102, 102));
        keywords.put(" do", new Color(255, 102, 102));
    }
    public Object get(Object key){
        return keywords.get(key);
    }
    public boolean containsKey(Object key){
        return keywords.containsKey(key);
    }
}