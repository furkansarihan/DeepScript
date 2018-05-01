package src;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextArea{
    /* public LineList lines; // List of all lines in text area. */
    public LinkedList<Line> lines;
    public ArrayList<Integer> selectedLines = new ArrayList<>();
    public int cursorLine;
    
    GraphicsContext gc;
    public Color backColor;
    public Color selectColor;
    
    /* Varibles */
    public int y; // Y coordinate of Line
    public int x; // X coordinate of each char
    public int heigth = 16;
    public int width = 10;
    public boolean removeOP;
    /* Varibles */
    
    public TextArea(Color bc, Color sc, GraphicsContext gcc){
        cursorLine = 0;
        lines = new LinkedList<Line>(); // Creating emty LineList
        lines.add(new Line(cursorLine));
        backColor = bc;
        selectColor = sc;
        gc = gcc;
        y = heigth;
    }
    /*      -Adding New Line-
     *
     * 1- Clear the canvas, from cursor to right.
     * 2- Get char list, from cursor to right.
     * 
     **/
    public void addLine(){
        gc.setFill(backColor);
        gc.fillRect(lines.get(cursorLine).cursorChar*width, cursorLine*heigth, 1000, heigth);
        //----------------------------------
        lines.add(cursorLine+1, new Line(lines.get(cursorLine).getCharListFromCursor(),cursorLine));
        cursorLine++; // new Line added
        if (cursorLine < lines.size()-1) renderToEnd();
        renderCurrentLine();
    }
    public void clearCursor(){ // Not used now
        gc.setFill(backColor);
        gc.fillRect(lines.get(cursorLine).cursorChar*width, cursorLine*heigth, 100, heigth);
    }
    public void type(char c){
        System.out.println("TextArea");
        lines.get(cursorLine).type(c, cursorLine);
    }
    public void renderCurrentLine(){
        System.out.println("Rendered line is : "+cursorLine);
        Line l = lines.get(cursorLine);
        x = 0;
        clearLine();
            for (Char c : l.chars) {
                gc.setFill(c.color);
                gc.fillText(c.toString(), x, cursorLine*heigth+heigth-3);
                x += width;
            }
        if (false) gc.fillRect(l.cursorChar*width, cursorLine*heigth, 4, heigth);
    }
    public void renderToEnd(){
        gc.setFill(backColor);
        gc.fillRect(0, (cursorLine+1)*heigth-3, 1000, heigth*(lines.size()-cursorLine));
        Line l;
        int i = cursorLine;
        do{
        System.out.println("-- Rendered line is : "+i);
        l = lines.get(i);
        x = 0;
        clearLine();
            for (Char c : l.chars) {
                gc.setFill(c.color);
                gc.fillText(c.toString(), x, i*heigth+heigth-3);
                x += width;
            }
        i ++;
        }while(i!=lines.size());
    }
    public void renderLine(int index){
        Line l = lines.get(index);
        x = 0;
        clearLine();
            for (Char c : l.chars) {
                if (c.isSelected()) gc.setFill(selectColor);
                else gc.setFill(backColor);
                gc.fillRect(x, index*heigth-3, width, heigth);
                gc.setFill(c.color);
                gc.fillText(c.toString(), x, index*heigth+heigth-3);
                x += width;
            }
        if (false) gc.fillRect(l.cursorChar*width, cursorLine*heigth, 4, heigth);
    }
    public void clearLine(){
        gc.setFill(backColor); // Firstly clear the line with bacground color
        gc.fillRect(0, cursorLine*heigth, 800, heigth);
    }
    public boolean remove(){ // returns true when line is deleted
        return lines.get(cursorLine).remove(cursorLine); // True when able to delete empty line
    }
    public void selectText(ArrayList<Point> list){
        //Selecting between two characters.
        //Firstly detect cursorLine and cursorChar positions 
        int cl1, cc1, cl2, cc2; // CursorLines and cursorChars
        int X, Y; // 
        cc1 = (int)list.get(0).getX()/width;
        cl1 = (int)list.get(0).getY()/heigth;
        cc2 = (int)list.get(1).getX()/width;
        cl2 = (int)list.get(1).getY()/heigth;
        if (cl1 == cl2){
            if (cc1 > cc2) lines.get(cl1).selectFromTo(cc2, cc1);
            else lines.get(cl1).selectFromTo(cc1, cc2);
        }else{
            if(cl1 > cl2){
                lines.get(cl2).selectFrom(cc2);
                int iter = cl2 + 1; // Starting selectAll from next line
                while(iter != cl1){
                    lines.get(iter).selectAll();
                    iter += 1;
                }
                lines.get(cl1).selectTo(cc1);
            }else{
                lines.get(cl1).selectFrom(cc1);
                int iter = cl1 + 1;
                while(iter != cl2){
                    lines.get(iter).selectAll();
                    iter += 1;
                }
                lines.get(cl2).selectTo(cc2);
            }
        }
        renderLine(cl1);
        if (cl2 > cl1){
            do{
                selectedLines.add(cl1);
                renderLine(cl1);
                cl1 += 1;
            }while(cl1 != cl2+1);
        }else{
            do{
                selectedLines.add(cl2);
                renderLine(cl2);
                cl2 += 1;
            }while(cl2 != cl1+1);
        }
    }
    public void resetSelect(){
        for (Integer i : selectedLines) {
            lines.get(i).deselectAll();
            renderLine(i);
        }
        selectedLines.remove(selectedLines);
    }
    public void moveCursor(int i){
        lines.get(cursorLine).moveCursor(i);
    }
    public void deleteCurrentLine(){
        LinkedList<Char> charsBefore = lines.get(cursorLine-1).chars;
        LinkedList<Char> charsRemoved = lines.get(cursorLine).getCharListFromCursor();
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
