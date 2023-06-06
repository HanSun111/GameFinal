import java.awt.*;
import javax.swing.*;
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
    public boolean isAttacking;
    public BufferedImage spriteSheet;
    public int animationDelay;
    public String spriteName;
    public Timer animationTimer;


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

    public void updateAnimationReverse() {
        currentFrame--;
        if (currentFrame < totalFrames) {
            currentFrame = totalFrames;
        }
    }

}
