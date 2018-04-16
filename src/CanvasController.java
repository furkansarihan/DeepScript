package src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Window;

import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class CanvasController implements Initializable {
    @FXML
    private Canvas textCanvas;
    @FXML
    private Canvas indexCanvas;
    
    public Font font;
    public Color backColor = new Color(0.122, 0.122, 0.122,1.0);
    public Color indexColor = new Color(0.500, 0.500, 0.500,1.0);
    public int TAX = 1920; // Text area width
    public int TAY = 1080; // Text area heigth
    
    public int TAXI = 60; // Text area width
    public int TAYI = 600; // Text area heigth
    
    public int fontSize = 16;

     /* Objects */
    public Nizam main; // ?
    public Scene scene;
    public TextArea text; // Source text area
    private GraphicsContext gc;
    private GraphicsContext gcIc; // IndexCanvas
     /* Objects */

    @FXML
    private void onKeyTyped(KeyEvent event) {
        char c = event.getCharacter().toCharArray()[0];
        if(c>31&&c<126){
            System.out.println(c);
            System.out.println(c);
            System.out.println("Handle icerisi "+c);
            text.type(c);
            text.renderCurrentLine(gc,true);// Renders the changes on current line
        }
    }
    @FXML
    private void onKeyPressed(KeyEvent event) {
            String s = event.getCharacter();
            KeyCode ke = event.getCode();
            System.out.println(s);
            if(ke.equals(KeyCode.ENTER)){
                text.addLine(gc);
                drawIndex();
            }else if(ke.equals(KeyCode.BACK_SPACE)){
                if(text.remove()){
                text.renderCurrentLine(gc,false);
                text.deleteCurrentLine();
                text.renderToEnd(gc);
                }
                text.renderCurrentLine(gc,true);
                drawIndex();
            }else if(ke.equals(KeyCode.LEFT)){
                text.moveCursor(-1);text.renderCurrentLine(gc,true);
            }else if(ke.equals(KeyCode.RIGHT)){
                text.moveCursor(1);text.renderCurrentLine(gc,true);
            }else if(ke.equals(KeyCode.UP)){
                text.renderCurrentLine(gc,false);
                text.upKey();
                text.renderCurrentLine(gc,true);
            }else if(ke.equals(KeyCode.DOWN)){
                text.renderCurrentLine(gc,false);
                text.downKey();
                text.renderCurrentLine(gc,true);
            }
    }
    @FXML
    private void onMouseEntered(MouseEvent event) {
        scene.setCursor(Cursor.TEXT);
    }
    @FXML
    private void onMouseExited(MouseEvent event) {
        scene.setCursor(Cursor.DEFAULT);
    }
    @FXML
    private void mouseAction(MouseEvent event) {
        System.out.println("MOUSE");
        System.out.println(text.lines.getFirst().cursorChar);
        System.out.println("MOUSE");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text = new TextArea(backColor); // Creating empty text area
        createCanvas();
        drawIndex();
    }
    public void createCanvas(){
        font = new Font("Courier New", fontSize);
        /* Text Canvas */
        gc = textCanvas.getGraphicsContext2D();
        gc.setFont(font);
        gc.setFill(backColor);
        gc.fillRect(0, 0, TAX, TAY);
        /* Text Canvas */
        
        /* Index Canvas */
        gcIc = indexCanvas.getGraphicsContext2D();
        gcIc.setFont(font);
        gcIc.setFill(backColor);
        gcIc.fillRect(0, 0, TAXI, TAYI);
        /* Index Canvas */
    }
    public void drawIndex(){
        gcIc.setFill(backColor);
        gcIc.fillRect(0, 0, TAXI, TAYI);
        int top = text.lines.size();
        int i = 0;
        int y = fontSize-4;
        gcIc.setFill(indexColor);
        while(i != top){
            gcIc.fillText(Integer.toString(i), 20, y);
            i++;
            y += fontSize;
        }
    }
    public void setObjects(Scene s){
        scene = s;
    }
    public void focus(){
        textCanvas.requestFocus();
    }
}