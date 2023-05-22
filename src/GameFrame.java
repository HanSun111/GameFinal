import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GameFrame extends JFrame {
    Image image;
    Graphics graphics;
    Box player;
    Box enemy;
    boolean gameOver;
    GameFrame(){
        //adds key listener

        // trial for game icon
        ImageIcon icon = new ImageIcon("game icon/game icon.png");
        this.setIconImage(icon.getImage());
        this.setTitle("Game");
        this.setSize(1800,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //without it, p changes all background to black.
        //this.setLayout(null);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);



    }
    public void paint(Graphics g){

    }

    public void checkCollision(){

    }
    public class AL extends KeyAdapter{

    }

}
