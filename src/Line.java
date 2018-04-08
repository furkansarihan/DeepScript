package src;

import java.util.LinkedList;

public class Line{

    /*public CharList chars = null;*/
    public LinkedList<Char> chars = null;
    public int cursorChar;

    public Line nextLine = null; // Will be implemented with line operations 
    public Line prevLine = null; // Will be implemented with line operations

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
}