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
        chars = new LinkedList<Char>();
        chars.add(new Char((char)10,cursorLine,cursorChar)); // For always beeing element of the list
        cursorChar = 0;
    }

    //Creating line with some String. 
    //ex: Pressing enter without being end of the line.
    public Line(LinkedList<Char> llc, int cursorLine){
        chars = llc;
        chars.add(new Char((char)10,cursorLine,cursorChar));
        cursorChar = 0;
    }
    public boolean remove(int cursorLine){
        if (cursorChar != 0){ // Basic delete
            chars.remove(cursorChar-1);
            cursorChar--;
            shiftCursorCharsLeft();
            return false;
        }else{
            return cursorLine != 0; // Cursor going to up line and deleted current line
        }
        
    }
    public void type(char c, int cursorLine){
        chars.add(cursorChar, new Char(c, cursorLine, cursorChar));
        cursorChar++;
        // Shifting cursor char indexes when typed inside the line
        if (cursorChar != chars.size()-1) shiftCursorCharsRigth();
    }
    public void shiftCursorCharsRigth(){
        for (int i = cursorChar; i < chars.size(); i++)
            chars.get(i).cursorChar++;
    }
    public void shiftCursorCharsLeft(){
        for (int i = cursorChar; i < chars.size(); i++)
            chars.get(i).cursorChar--;
    }
    public void moveCursor(int i){
        if ( cursorChar != chars.size()-1 && i>0 && chars.size() != 0) cursorChar++;
        else if (cursorChar > 0  && i<0) cursorChar--;
    }
    public LinkedList<Char> getCharListFromCursor(){
        LinkedList<Char> temp = new LinkedList<>();
        if (cursorChar<chars.size()){
            while(cursorChar != chars.size()-1){
                temp.add(chars.get(cursorChar));
                chars.remove(cursorChar);
            }
        }
        return temp;
    }
}