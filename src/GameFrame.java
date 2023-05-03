import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){
        ImageIcon icon = new ImageIcon("game icon/game icon.png");
        this.setIconImage(icon.getImage());
        this.setTitle("A Game ");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
