import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Units{
    GamePanel gP;
    KeyHandler kH;

    Image idle, runR, runL, jump, attack1, attack2, fall, death, takeHit, takeHitWhite;
    //temp
    public Player(GamePanel gp, KeyHandler kh){
        gP = gp;
        kH = kh;

        attack1 = new ImageIcon("player/Attack1.png").getImage();
        attack2 = new ImageIcon("player/Attack2.png").getImage();
        death = new ImageIcon("player/Death.png").getImage();
        fall = new ImageIcon("player/Fall.png").getImage();
        jump = new ImageIcon("player/Jump.png").getImage();
        runR = new ImageIcon("player/Run.png").getImage();
        runL = new ImageIcon("player/Run.png").getImage();
        idle = new ImageIcon("player/Idle.png").getImage();
        takeHit = new ImageIcon("player/Take Hit.png").getImage();
        takeHitWhite = new ImageIcon("player/Take Hit - white silhouette.png").getImage();


        setDefaultValues();
        //getPlayerImage();
    }

    public void setDefaultValues(){
        xCoord = 950;
        yCoord = 420;
        speed = 5;
        animation = "idle";
    }

    public void update() {
        //long start = System.nanoTime();
        //System.out.println(start);
        animation = "idle";
        if (kH.left && xCoord > -20) {
            xCoord -= speed;
            animation = "runL";
            System.out.println("left");
            if(kH.jump){
                speed = 15;
            }
            else speed = 5;
        }
        if (kH.right && xCoord < 1720) {
            xCoord += speed;
            animation = "runR";
            System.out.println("right");
            if(kH.jump){
                speed = 15;
            }
            else speed = 5;
        }
        if (kH.jump && yCoord > 250) {
            animation = "jump";
            yCoord -= 25;
            System.out.println("jump");
        }

        if (yCoord < 420 && !kH.jump) {
            try {
                Thread.sleep(8);
                yCoord += 10;
            } catch (InterruptedException e) {
                e.printStackTrace();
                // keeps running;
                new Thread((Runnable) this).start();
                System.exit(0);
            }
        }

    }
    public void draw(Graphics2D g2) {

        Image image = switch (animation) {
            case "runR" -> runR;
            case "runL" -> runL;
            case "jump" -> jump;
            case "idle" -> idle;
            default -> null;
        };

        int hitBoxX = xCoord;
        int hitBoxY = yCoord;
        g2.setColor(Color.white);
        g2.fillRect(hitBoxX + 84, hitBoxY + 69, 28,55);

        g2.drawImage(image, xCoord, yCoord, null);

    }

}
