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
    public Line(){
        /*chars = new CharList();*/
        chars = new LinkedList<Char>();
        chars.add(new Char((char)10)); // For always beeing element of the list
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
            cursorChar = cursorChar - 1;
            return false;
        }else{
            if(cursorLine != 0){ // Cursor going to up line and deleted current line
                return true;
            }else{
                return false;
            }
        }
        
    }
    public void type(char c){
        System.out.println("Line");
        if (cursorChar == 0) {
            chars.addFirst(new Char(c));
            //System.out.println("cursor 0"); 
            cursorChar++;
        }
        else {
            chars.add(cursorChar, new Char(c));
            //System.out.println("middle");
            cursorChar++;
        }
    }
    public void moveCursor(int i){
        if ( cursorChar != chars.size()-1 && i>0 ) cursorChar++;
        else if (cursorChar > 0 ) cursorChar--;
    }
}