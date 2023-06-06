import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SpriteSheetAnimation extends JPanel {

    private BufferedImage spriteSheet;
    private int spriteWidth;
    private int spriteHeight;
    // number is reversed when using flipped animations
    private int currentFrame;
    // changes depending on sprite
    private int totalFrames;
    private int delay;
    public String spriteName;

    public SpriteSheetAnimation() {
        //example
        spriteName = "Idlef";
        // Load your sprite sheet image
        spriteSheet = loadImage("player/" + spriteName + ".png");

        // Set sprite width and height
        spriteWidth = 200;
        spriteHeight = 200;

        // Set initial frame and total frames
        currentFrame = 0;
        totalFrames = 8;

        // Set animation delay (in milliseconds)
        delay = 100;

        // Create a timer to update the animation
        Timer timer = new Timer(delay, e -> {
            updateAnimation();
            repaint();
        });
        timer.start();
    }

    private BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateAnimation() {
        currentFrame++;
        if (currentFrame >= totalFrames) {
            currentFrame = 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the current frame's coordinates in the sprite sheet
        int sx = (currentFrame % (getWidth() / spriteWidth)) * spriteWidth;
        int sy = (currentFrame / (getWidth() / spriteWidth)) * spriteHeight;

        // Draw the current frame
        g.drawImage(spriteSheet, 0, 0, getWidth(), getHeight(), sx, sy, sx + spriteWidth, sy + spriteHeight, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(spriteWidth, spriteHeight);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sprite Sheet Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpriteSheetAnimation animation = new SpriteSheetAnimation();
        frame.add(animation);
        frame.pack();
        frame.setVisible(true);
    }
}
