import javax.swing.*;

public class GameFrame extends JFrame {
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


}
