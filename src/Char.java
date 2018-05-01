package src;

import javafx.scene.paint.Color;

public class Char{
    /* Varibles */
    public char ch;
    public Color color = new Color(0.811, 0.811, 0.811,1.0); // Drawing color of char
    public boolean selected = false;
    /* Varibles */
    
    public Char(char c){
        ch = c;
    }
    public String toString(){
        return Character.toString(ch);
    }
    public void select(){
        selected = true;
    }
    public void deselect(){
        selected = false;
    }
    public boolean isSelected(){
        return selected;
    }
}