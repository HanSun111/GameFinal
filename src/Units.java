import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Units {
    public int xCoord;
    public int yCoord;
    public int speed;
    public int spriteW;
    public int spriteH;
    public int currentFrame;
    public int totalFrames;
    public String animation;
    public String direction;
    public int damage;
    public int health;
    public BufferedImage spriteSheet;
    public int animationDelay;
    public String spriteName;

    public BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateAnimation() {
        currentFrame++;
        if (currentFrame >= totalFrames) {
            currentFrame = 0;
        }
    }

//    public Dimension getPreferredSize() {
//        return new Dimension(spriteW, spriteH);
//    }

//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        // Get the current frame's coordinates in the sprite sheet
//        int sx = (currentFrame % (spriteSheet.getWidth() / spriteW)) * spriteW;
//        int sy = (currentFrame / (spriteSheet.getWidth() / spriteW)) * spriteH;
//
//        // Draw the current frame
//        g.drawImage(spriteSheet, 0, 0, spriteSheet.getWidth(), spriteSheet.getHeight(), sx, sy, sx + spriteW, sy + spriteH, null);
//    }
}
