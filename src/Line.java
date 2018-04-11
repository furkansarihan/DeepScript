package src;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line{

    /* Varibles */
    public LinkedList<Char> chars = null;
    public int cursorChar;
    /* Varibles */

    //Creating empty line
    public Line(int cursorLine){
        /*chars = new CharList();*/
        chars = new LinkedList<Char>();
        chars.add(new Char((char)10,cursorLine,cursorChar)); // For always beeing element of the list
        cursorChar = 0;
    }

    //Creating line with some String. 
    //ex: Pressing enter without being end of the line.
    public Line(LinkedList<Char> llc){
        //
    }
    public boolean remove(int cursorLine){
        if (cursorChar != 0){ // Basic delete
            chars.remove(cursorChar-1);
            cursorChar--;
            shiftCursorCharsLeft();
            return false;
        }else{
            if(cursorLine != 0){ // Cursor going to up line and deleted current line
                return true;
            }else{
                return false;
            }
        }
        
    }
    public void type(char c, int cursorLine){
        chars.add(cursorChar, new Char(c, cursorLine, cursorChar));
        cursorChar++;
        if (cursorChar != chars.size()-1) 
            shiftCursorCharsRigth(); // Shifting cursor char indexes when typed inside the line
    }
    public void shiftCursorCharsRigth(){
        for (int i = cursorChar; i < chars.size(); i++) {
            chars.get(i).cursorChar++;
        }
    }
    public void shiftCursorCharsLeft(){
        for (int i = cursorChar; i < chars.size(); i++) {
            chars.get(i).cursorChar--;
        }
    }
    public void moveCursor(int i){
        if ( cursorChar != chars.size()-1 && i>0 ) cursorChar++;
        else if (cursorChar > 0 ) cursorChar--;
    }
}