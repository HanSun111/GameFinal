import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {
    GameFrame(){
        //adds key listener
        this.addKeyListener(this);


        JPanel p = new JPanel();
        p.setBackground(Color.black);
        p.setBounds(400, 160, 75, 160);

        // trial for game icon
        ImageIcon icon = new ImageIcon("game icon/game icon.png");
        this.setIconImage(icon.getImage());

        this.setTitle("A Game");
        //without it, p changes all background to black.
        this.setLayout(null);
        this.setSize(1800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        this.add(p);


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
