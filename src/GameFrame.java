import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class GameFrame extends JFrame {
    GameFrame(){

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("IDK it's a Game");

        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gamePanel.startThread();
    }

}
