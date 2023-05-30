import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Units{
    GamePanel gP;
    KeyHandler kH;
    Image attack1;
    Image idle;
    //temp
    public Player(GamePanel gp, KeyHandler kh){
        gP = gp;
        kH = kh;

        attack1 = new ImageIcon("player/Attack1.png").getImage();

        idle = new ImageIcon("player/Idle.png").getImage();

        setDefaultValues();
        //getPlayerImage();
    }

    public void setDefaultValues(){
        xCoord = 950;
        yCoord = 420;
        speed = 5;
        direction = "right";
    }

//    public void getPlayerImage(){
//
//        try{
//            death = ImageIO.read(getClass().getResourceAsStream("/player/Death.png"));
//            idle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Idle.png")));
//            run = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Run.png")));
//            jump = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Jump.png")));
//            attack1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Attack1.png")));
//            attack2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Attack2.png")));
//            fall = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Fall.png")));
//            takeHit = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Take Hit.png")));
//            takeHitWhite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Take Hit - white silhouette.png")));
//
//
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }


    public void update() {
        long start = System.nanoTime();
        System.out.println(start);
        if (kH.left && xCoord > -20) {
            xCoord -= speed;
            direction = "left";
            System.out.println("left");
        }
        if (kH.right && xCoord < 1720) {
            xCoord += speed;
            direction = "right";
            System.out.println("right");
        }
        if (kH.jump) {
            direction = "jump";
            yCoord -= speed * speed;
            System.out.println("jump");
        }
        if (yCoord != 420 && start > 1000000000) {
            while (yCoord != 420) {
                yCoord -= speed;
            }
        }
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(attack1, xCoord, yCoord, null);
//            g2.fillRect(xCoord, yCoord, 50,100 );

        BufferedImage image = switch (direction) {
            case "left", "right" -> run;
            case "jump" -> jump;
            default -> null;
        };

        g2.drawImage(image, xCoord, yCoord, null);
    }

}
