package src;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextArea{
    /* public LineList lines; // List of all lines in text area. */
    public LinkedList<Line> lines;
    public int cursorLine;
    
    public Color backColor;

    /* Varibles */
    public int y; // Y coordinate of Line
    public int x; // X coordinate of each char
    public int heigth;
    public int width = 10;
    public boolean removeOP;
    /* Varibles */
    
    public TextArea(Color bc){
        cursorLine = 0;
        lines = new LinkedList<Line>(); // Creating emty LineList
        lines.add(new Line(cursorLine));
        backColor = bc;
        heigth = 16;
        y = heigth;
    }

    public void addLine(){
        lines.add(cursorLine+1, new Line(cursorLine));
        cursorLine++; // new Line added
        y += heigth;
    }
    public void clearCursor(GraphicsContext gc){
        gc.setFill(backColor);
        gc.fillRect(lines.get(cursorLine).cursorChar*width, y-heigth, 1, heigth);
    }
    public void type(char c){
        System.out.println("TextArea");    
        lines.get(cursorLine).type(c, cursorLine);
    }
    public void renderCurrentLine(GraphicsContext gc, boolean drawCursor){
        System.out.println(cursorLine);
        Line l = lines.get(cursorLine);
        x = 0;
        y = y-4;
        clearLine(gc);
            for (Char c : l.chars) {
                gc.setFill(c.color);
                gc.fillText(c.toString(), x, y);
                x += width;
            }
        y = y+4;
        if (drawCursor) gc.fillRect(l.cursorChar*width, y-heigth, 1, heigth);
        //if(b) renderLinesAfter();
    }
    public void clearLine(GraphicsContext gc){
        int yy = y;
        if(cursorLine == 0){
            yy += 4;
        }
        gc.setFill(backColor); // Firstly clear the line with bacground color
        gc.fillRect(0, cursorLine*heigth, 800, heigth);
    }
    public boolean remove(){ // returns true when line is deleted
        return lines.get(cursorLine).remove(cursorLine); // True when able to delete empty line
    }
    public void print(){
        for (Char c : lines.get(cursorLine).chars) {
            System.out.print(c.ch);
        }
        System.out.println();
    }
    public void moveCursor(int i){
        lines.get(cursorLine).moveCursor(i);
    }
    public void deleteCurrentLine(){
        lines.remove(cursorLine); // Remove from list
        cursorLine--; // Decrease the cursorLine index
        y -= heigth; // Update drawing refference
    }
    public void upKey(){
        if (cursorLine != 0) {
            cursorLine --; 
            y-=heigth;
        }
    }
    public void downKey(){
        if (cursorLine != lines.size()-1) {
            cursorLine ++;
            y+=heigth; 
        }
    }
}
