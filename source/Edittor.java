import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.List;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class Edittor extends JPanel implements KeyListener {
    
/***************************************************************/
    public final int WIDTH = 480, HEIGTH = 640;
/***************************************************************/

    public String source = "";
    public int cursorIndex = 0;
    public Color bgColor = new Color(38, 38, 38);
    public Color txColor = new Color(230, 230, 230);
    public Color crsColor = new Color(209, 224, 224);
    public Color countColor = new Color(128, 128, 128);
    public Font font = new Font("Corbel",Font.PLAIN, 14);
    public int lineCount = 1; // Default 1

/***************************************************************/
    Edittor(){
        setSize(480,640);
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
    }
/***************************************************************/
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {  backspace(); repaint(); }
        
        else { type(e.getKeyChar()); }
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {  leftCursor(); repaint(); }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {  rigthCursor(); repaint(); }
        //else if (e.getKeyCode() == KeyEvent.VK_ENTER) checkLineCount();
    }
    public void keyReleased(KeyEvent e) {}
/***************************************************************/
    public void rigthCursor(){
        if(source.length()>0 && cursorIndex<source.length()) cursorIndex++;
    }
    public void leftCursor(){
        if(source.length()>0 && cursorIndex>0) cursorIndex--;
    }
    public void backspace(){ 
        if(source != null && source.length()>0) {
            source = source.substring(0, source.length()-1);
            cursorIndex--; }
    }
    public void type(char c){
        //System.out.println(source.length()+""+cursorIndex);
        if(!(source.length()==cursorIndex)){
            String s = new String (source.substring(0,cursorIndex) + c + source.substring(cursorIndex,source.length()));
            source = s;
        }else source += c;
        cursorIndex++;
        repaint();
        //System.out.println(source);
    }
    @Override
    public void paint(java.awt.Graphics g){
        drawSource(g);
    }
    void drawSource(java.awt.Graphics g){ // render source text
        g.setColor(bgColor);
        g.fillRect(0, 0, WIDTH, HEIGTH);
        g.setColor(txColor);
        g.setFont(font);
        int height = g.getFontMetrics().getHeight();
        char[] arr= source.toCharArray();
        Point p = new Point(48,height*2);
        int x = 0; lineCount = 1;
        
        if(source == "" || source.length() == 0) drawCursor(p,g);

        for (int i=0;i<source.length();i++) {
            x = g.getFontMetrics().stringWidth(arr[i]+"");
            if (arr[i] == 10) { p = enterTyped(p,height); lineCount++;}
            else { g.drawString(arr[i]+"",p.x,p.y); p = charTyped(p,x); }
            if (i == cursorIndex-1) drawCursor(p,g);
        }
        DrawLineIndex(g);
    }
    public void drawCursor(Point p, java.awt.Graphics g){
        g.setColor(crsColor); g.drawString("|",p.x,p.y); g.setColor(txColor);
    }
    public Point enterTyped(Point p, int CHARY){ p.x = 48; p.y += CHARY; return p; }
    public Point charTyped(Point p, int CHARX){ p.x += CHARX; return p; }
/***************************************************************/
public void DrawLineIndex(Graphics g){ //Draws line index numbers 
    int h = g.getFontMetrics().getHeight();
    Point p = new Point(16,h*2);
    g.setColor(countColor);
    for(int i=0;i<lineCount;i++){
        g.drawString((i+1)+"", p.x, p.y);
        p.y += h;
    }
    g.setColor(txColor);
}
/***************************************************************/
static class Frame extends JFrame {
    public final Dimension d = new Dimension(480,640);
    public Frame() {
        setLocation(300,100);
        setBackground(Color.BLACK);
        setSize(d);setPreferredSize(d);
        Edittor e = new Edittor();
        e.addKeyListener(e);
        e.setFocusable(true);
        add(e);pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
/***************************************************************/
    public static void main(String[] args){
        new Frame();
    }
}