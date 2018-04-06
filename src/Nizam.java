package src;

import java.util.LinkedList;

import javax.xml.ws.handler.HandlerResolver;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class Nizam extends Application{
    public TextArea text; // Source text area
    public Canvas textCanvas; // Draw Area
    public GraphicsContext gc; // 
    Scene scene;
    public Group root;

    @Override 
    public void start(Stage s) {
        initilaize(s); // Initial
        createCanvas(); // Create drawable object Canvas

    }
    void print(java.util.List list){
        System.out.println(list);
    }
    public void initilaize(Stage stage){
        stage.setTitle("My first javafx example");
        stage.setWidth(800);
        stage.setHeight(600);
        root = new Group();
        scene = new Scene(root);
        // Creating Objects
        text = new textArea(); // Creating empty text area
         
        setListeners();
        stage.setScene(scene);
        stage.show();
    }
    public void setListeners(){
        scene.setOnKeyPressed((new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // If typable character comes..
                if (event.getEventType().equals(KeyEvent.KEY_TYPED)) text.type(c);
                else if (false) ;
                else if (false) ;
                else if (false) ;
            }
        }));
    }
    public void createCanvas(){
        textCanvas = new Canvas(800,600);
        gc = textCanvas.getGraphicsContext2D();
        root.getChildren().add(textCanvas);
    }
    boolean drawText(String s, double x, double y){
        if (gc == null) return false;
        gc.fillText(s, x, y);
        return true;
    }

/******************************************************************************/
        public static void main(String[] args) {
            Application.launch(args);
        }
}