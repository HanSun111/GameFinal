import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel implements Runnable{
    //dimensions
    final int SCALE = 2;
    final int FPS = 60;

    KeyHandler kH = new KeyHandler();

    // game thread
    Thread gameThread;

    Player player = new Player(this, kH);

    //set default position of player
    int x = 950;
    int y = 450;
    int playerSpeed = 5;

    GamePanel() {
        this.setPreferredSize(new Dimension(1900,600));


        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kH);
        this.setFocusable(true);
    }

    public void draw(){

    }

public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
}
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //0.016666666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){
            long currentTime = System.nanoTime();
            //testing to see if game ran.
            // System.out.println(currentTime);
            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
        if(kH.left){
            x-=playerSpeed;
            System.out.println("left");
        }
        if(kH.right){
            x+=playerSpeed;
            System.out.println("right");
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
