package src;

import javafx.scene.paint.Color;

public class Char{
    /* Varibles */
    public char ch;
    public int cursorChar; // Setting upClass
    public Color color = new Color(0.811, 0.811, 0.811,1.0); // Drawing color of char
    /* Varibles */

    /*
        Font information also 
        could be stored here. 

    */
    
    public Char(char c){
        ch = c;
    }
    public String toString(){
        return Character.toString(ch);
    }
}