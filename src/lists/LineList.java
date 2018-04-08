package src;

public class LineList{

    public Line root = null; // First line so cursorIndex is 0.
    public int cursorLine; // Index of line that include cursor

    public LineList(){
        root = new Line();
    }

    public void addLine(){
        
    }
    public void type(char c){
        System.out.println("LineList");
        root.type(c);
    }

}