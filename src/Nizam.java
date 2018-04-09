package src;

import java.util.LinkedList;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class Nizam extends Application{

    /* Defines */
    public int TAX = 800; // Text area width
    public int TAY = 600; // Text area heigth
    /* Defines */

    /* Varibles */
    public float fontHeigth;
    public float fontWidth;
    /* Varibles */
    
    /* Objects */
    public Scene scene;
    public AnchorPane root;
    /* Objects */

    @Override 
    public void start(Stage s){
        try{
            initilaize(s); // Initial
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void initilaize(Stage stage) throws Exception{
        stage.setTitle("Nizam");
        stage.setWidth(TAX);
        stage.setHeight(TAY);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NizamLayout.fxml"));
        root = loader.load();
        CanvasController nc = loader.getController();
        System.out.println(nc);
        System.out.println(this);
        nc.setObjects(this);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        nc.focus();
    }
    
/******************************************************************************/
        public static void main(String[] args) {
            Application.launch(args);
        }
}