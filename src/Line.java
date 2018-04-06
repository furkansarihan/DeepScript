package src;

public class Line{

    CharList chars = null;
    int cursorChar = null;

    Line nextLine = null;
    Line prevLine = null;

    //Creating empty line
    public Line(){

    }

    //Creating line with some String. 
    //ex: Pressing enter without being end of the line.
    public Line(CharList cl){

    }

    public void type(char c){

        if (cursorChar == 0) chars.addFirst(c);
        else if (cursorChar+1 == chars.getSize()) chars.addLast(c);
        else addSomewhere(c);
    }
}