import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Units{
    GamePanel gP;
    KeyHandler kH;
    //temp
    public Player(GamePanel gp, KeyHandler kh){
        gP = gp;
        kH = kh;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        xCoord = 950;
        yCoord = 450;
        speed = 5;
        direction = "left";
    }

    public void getPlayerImage(){

        try{
            idle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("player/Idle.png")));
            run = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("player/Run.png")));
            jump = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("player/Jump.png")));
            attack1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("player/Attack1.png")));
            attack2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("player/Attack2.png")));
            fall = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("player/Fall.png")));
            takeHit = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("player/Take Hit.png")));
            takeHitWhite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("player/Take Hit - white silhouette.png")));



        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public void update() {

        if (kH.left) {
            xCoord -= speed;
            direction = "left";
            System.out.println("left");
        }
        if (kH.right) {
            xCoord += speed;
            direction = "right";
            System.out.println("right");
        }
        if (kH.jump) {
            direction = "jump";
            yCoord += speed;
            System.out.println("space");
        }
        if (yCoord != 450) {
            while (yCoord != 450) {
                yCoord -= speed;
            }
        }
    }
    public void draw(Graphics2D g2) {
//            g2.setColor(Color.black);
//            g2.fillRect(xCoord, yCoord, 50,100 );

        BufferedImage image = null;

        switch (direction) {
            case"left":
                image = run;
            case"right":
                image = run;
            case"":
                image = run;

        }
    }

}
