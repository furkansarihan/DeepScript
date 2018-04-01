import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

import javax.sound.midi.SysexMessage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;

public class Edittor extends JPanel implements KeyListener {
    
/***************************************************************/
    public final int WIDTH = 480, HEIGTH = 640;
/***************************************************************/

    public String source = "";
    public int cursorIndex = 0;

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
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {  leftCursor(); repaint(); }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {  rigthCursor(); repaint(); }
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
            source = source.substring(0,cursorIndex) + c + source.substring(cursorIndex,source.length()-1);
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGTH);
        g.setColor(Color.WHITE);
        char[] arr= source.toCharArray();
        Point p = new Point(20,25);

        int x = 0,y = 0;
        
        if(source == "" || source.length() == 0) drawCursor(p,g);

        for(int i=0;i<source.length();i++){
            x = g.getFontMetrics().stringWidth(arr[i]+"");
            switch (arr[i]) {
                case 10 :
                    p = enterTyped(p,g.getFontMetrics().getHeight());
                    break;
                default :
                    g.drawString(arr[i]+"",p.x,p.y);
                    p = charTyped(p,x);
            }
            
            if(i == cursorIndex) drawCursor(p,g);
        }
        //System.out.println(cursorIndex);
        //drawCursor(p,g);
    }
    public void drawCursor(Point p, java.awt.Graphics g){
        g.setColor(Color.GREEN); g.drawString("|",p.x,p.y); g.setColor(Color.WHITE);
    }
    public Point enterTyped(Point p, int CHARY){ p.x = 20; p.y += CHARY; return p; }
    public Point charTyped(Point p, int CHARX){ p.x += CHARX; return p; }
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