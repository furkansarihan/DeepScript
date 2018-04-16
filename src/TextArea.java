package src;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextArea{
    /* public LineList lines; // List of all lines in text area. */
    public LinkedList<Line> lines;
    public int cursorLine;
    
    public Color backColor;
    ArrayList<Integer> a = new ArrayList<>();
    
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
    /*      -Adding New Line-
     *
     * 1- Clear the canvas, from cursor to right.
     * 2- Get char list, from cursor to right.
     * 3- 
     * 
     * 
     **/
    public void addLine(GraphicsContext gc){
        gc.setFill(backColor);
        gc.fillRect(lines.get(cursorLine).cursorChar*width, cursorLine*heigth, 1000, heigth);
        //----------------------------------
        lines.add(cursorLine+1, new Line(lines.get(cursorLine).getCharListFromCursor(),cursorLine));
        cursorLine++; // new Line added
        if (cursorLine < lines.size()-1) renderToEnd(gc);
        renderCurrentLine(gc, true);
    }
    public void clearCursor(GraphicsContext gc){ // Not used now
        gc.setFill(backColor);
        gc.fillRect(lines.get(cursorLine).cursorChar*width, cursorLine*heigth, 100, heigth);
    }
    public void type(char c){
        System.out.println("TextArea");    
        lines.get(cursorLine).type(c, cursorLine);
    }
    public void renderCurrentLine(GraphicsContext gc, boolean drawCursor){
        System.out.println("Rendered line is : "+cursorLine);
        Line l = lines.get(cursorLine);
        x = 0;
        clearLine(gc);
            for (Char c : l.chars) {
                gc.setFill(c.color);
                gc.fillText(c.toString(), x, cursorLine*heigth+heigth-3);
                x += width;
            }
        if (drawCursor) gc.fillRect(l.cursorChar*width, cursorLine*heigth, 4, heigth);
    }
    public void renderToEnd(GraphicsContext gc){
        gc.setFill(backColor);
        gc.fillRect(0, (cursorLine+1)*heigth-3, 1000, heigth*(lines.size()-cursorLine));
        Line l;
        int i = cursorLine;
        do{
        System.out.println("-- Rendered line is : "+i);
        l = lines.get(i);
        x = 0;
        clearLine(gc);
            for (Char c : l.chars) {
                gc.setFill(c.color);
                gc.fillText(c.toString(), x, i*heigth+heigth-3);
                x += width;
            }
        i ++;
        }while(i!=lines.size());
    }
    public void clearLine(GraphicsContext gc){
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
        LinkedList<Char> charsBefore = lines.get(cursorLine-1).chars;
        LinkedList<Char> charsRemoved = lines.get(cursorLine).getCharListFromCursor();
        System.out.println("Burada silinme islemi olacak bu gece : ");
        for (Char c : charsRemoved) {
            charsBefore.add(charsBefore.size()-1, c);
            System.out.print(c);
        }
        lines.remove(cursorLine); // Remove from list
        cursorLine--; // Decrease the cursorLine index
    }
    public void upKey(){
        if (cursorLine != 0) cursorLine --;
    }
    public void downKey(){
        if (cursorLine != lines.size()-1) cursorLine ++;
    }
}
