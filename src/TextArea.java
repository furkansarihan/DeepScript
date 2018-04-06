package src;

public class TextArea{
    LineList lines; // List of all lines in text area.

    public TextArea(){
        lines = new LineList(); // Creating emty LineList
    }

    public void addLine(){

    }
    public void type(char c){ // Will countinue to (Line) and adding it's (CharList)
        lines.type(c);
    }

}