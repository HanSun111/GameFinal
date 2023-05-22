import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class GamePanel extends JPanel{
    //dimensions
    public static final int W = 900;
    public static final int H = 300;
    public static final int SCALE = 2;
    // game thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;
    private BufferedImage image;

    GamePanel() {
        super();
        setPreferredSize(new Dimension(W * SCALE, H * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void paint(Graphics g){

    }



}
