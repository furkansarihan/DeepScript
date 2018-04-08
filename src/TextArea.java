package src;

import java.util.LinkedList;

public class TextArea{
    /* public LineList lines; // List of all lines in text area. */
    public LinkedList<Line> lines;
    public int cursorLine;

    public TextArea(){
        lines = new LinkedList<Line>(); // Creating emty LineList
        lines.add(new Line());
        cursorLine = 0;
    }

    public void addLine(){
        lines.add(cursorLine, new Line());
        cursorLine++; // new Line added
    }

    public void type(char c){ // Will countinue to (Line) and adding it's (CharList)
        System.out.println("TextArea");    
        lines.get(cursorLine).type(c);;
    }

}
