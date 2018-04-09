package src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Window;

import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    public int TAX = 800; // Text area width
    public int TAY = 600; // Text area heigth
    public int fontSize = 16;

     /* Objects */
    public Nizam main;
    public TextArea text; // Source text area
    private GraphicsContext gc;
     /* Objects */

    @FXML
    private void onKeyPressed(KeyEvent event) {
        System.out.println("merhaba");
            if(event.getCode().equals(KeyCode.ENTER)){
                text.clearCursor(gc);
                text.addLine();
            }else if(event.getCode().equals(KeyCode.BACK_SPACE)){
                text.remove();
            }else if(event.getCode().equals(KeyCode.LEFT)){
                text.moveCursor(-1);
            }else if(event.getCode().equals(KeyCode.RIGHT)){
                text.moveCursor(1);
            }else{
                System.out.println(event.getText());
                System.out.println("Handle icerisi "+event.getText());
                text.type(event.getText().toCharArray()[0]);
            }
            text.renderCurrentLine(gc);// Renders the changes on current line
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
    }
    public void createCanvas(){
        font = new Font("Courier New", fontSize);
        gc = textCanvas.getGraphicsContext2D();
        gc.setFont(font);
        gc.setFill(backColor);
        gc.fillRect(0, 0, TAX, TAY);
    }
    public void setObjects(Nizam n){
        main = n;
    }
    public void focus(){
        textCanvas.requestFocus();
    }
}