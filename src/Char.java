package src;

import javafx.scene.paint.Color;

public class Char{
    /* Varibles */
    public char ch;
    public int cursorLine;
    public int cursorChar; // Setting upClass
    public Color color = new Color(0.811, 0.811, 0.811,1.0); // Drawing color of char
    public boolean selected;
    /* Varibles */
    
    public Char(char c, int cChar, int cLine){
        ch = c;
        cursorChar = cChar;
        cursorLine = cLine;
        selected = false;
    }
    public String toString(){
        return Character.toString(ch);
    }
    public boolean isSelected(){
        return selected;
    }
}