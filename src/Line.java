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
        chars.add(new Char((char)10)); // For always beeing element of the list
        cursorChar = 0;
    }

    //Creating line with some String. 
    //ex: Pressing enter without being end of the line.
    public Line(LinkedList<Char> llc, int cursorLine){
        chars = llc;
        chars.add(new Char('\r'));
        cursorChar = 0;
    }
    public boolean remove(int cursorLine){
        if (cursorChar != 0){ // Basic delete
            chars.remove(cursorChar-1);
            cursorChar--;
            return false;
        }else{
            return cursorLine != 0; // Cursor going to up line and deleted current line
        }
        
    }
    public void type(char c, int cursorLine){
        chars.add(cursorChar, new Char(c));
        cursorChar++;
    }
    public void selectAll(){
        for (Char c : chars) {
            c.select();
        }
    }
    public void selectFrom(int from){
        while(from != chars.size()){
            chars.get(from).select();
            from++;
        }
    }
    public void selectTo(int to){
        int iter = 0;
        while(iter != to){
            chars.get(iter).select();
            iter++;
        }
    }
    public void selectFromTo(int from, int to){
        while(from != to){
            chars.get(from).select();
            from++;
        }
    }
    public void deselectAll(){
        for (Char c : chars) {
            c.deselect();
        }
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