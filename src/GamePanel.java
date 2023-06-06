import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class GamePanel extends JPanel implements Runnable{
    Timer timer;
    Image background;
    Image backgroundModified;

    final int FPS = 60;

    KeyHandler kH = new KeyHandler();
    MouseHandler mH = new MouseHandler();

    // game thread
    Thread gameThread;

    Player player = new Player(this, kH, mH);
    Enemies enemies = new Enemies(this);

    GamePanel() {
        this.setPreferredSize(new Dimension(1900,600));
        // sets background to size of the dimensions;
        background = new ImageIcon("Background/forest.png").getImage();
        backgroundModified = background.getScaledInstance(1900, 600, Image.SCALE_SMOOTH);

        this.setDoubleBuffered(true);
        this.addKeyListener(kH);
        this.addMouseListener(mH);
        this.setFocusable(true);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(backgroundModified, 0, 0, null);

        //character
        g2.fillRect(player.hitBoxX, player.hitBoxY, 150, 55);
        player.draw(g2);
        enemies.draw(g2);

        g2.dispose();
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

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
                //sometimes error occurs when time is zero

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
        player.update();
        boolean intersecting = player.playerHitBox.intersects(enemies.enemyHitBox);
        if(intersecting){
            enemies.animation = "atk";
        }
        if(enemies.xCoord < player.xCoord && intersecting){
            enemies.direction = "R";
            enemies.animation = "walk";
            enemies.xCoord += 5;
        }
        if(enemies.xCoord > player.xCoord && intersecting){
            enemies.direction = "L";
            enemies.animation = "walk";
            enemies.xCoord -= 5;
        }
    }

}
