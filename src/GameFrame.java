import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class GameFrame extends JFrame {
    Image image;
    Graphics graphics;
    boolean gameOver;
    GameFrame(){
        //frame and panel as game.

        // trial for game icon
        ImageIcon icon = new ImageIcon("game icon/game icon.png");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setIconImage(icon.getImage());
        this.setTitle("IDK it's a Game");

        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gamePanel.startThread();



    }
    public void paint(Graphics g){

    }

    public void checkCollision(){

    }
    public class AL extends KeyAdapter{

    }

}
