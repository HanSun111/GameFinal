import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable{
    //dimensions
    final int SCALE = 2;

    KeyHandler kH = new KeyHandler();

    // game thread
    Thread gameThread;

    //set default position of player
    int x = 950;
    int y = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(1900,700));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kH);
        this.setFocusable(true);
    }

    public void paint(Graphics g){

    }

    public void draw(){

    }

public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();;
}
    @Override
    public void run() {

        while (gameThread != null){

            //System.out.println("AHHHHH");
            //undates information on character position
            update();
            //draw
            repaint();
        }
    }
    public void update(){
        if(kH.left){
            x-=playerSpeed;
        }
        if(kH.right){
            x+=playerSpeed;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(x, y, 50,100 );
        g2.dispose();
    }
}
