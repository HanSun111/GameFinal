

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Units{
    GamePanel gP;
    KeyHandler kH;
    MouseHandler mH;
    Image idle, run, jump, attack1, attack2, fall, death, takeHit, takeHitWhite;
    Image idleF, runF, jumpF, attack1F, attack2F, fallF, deathF, takeHitF, takeHitWhiteF;
    //temp
    public Player(GamePanel gp, KeyHandler kh, MouseHandler mh){
        gP = gp;
        kH = kh;
        mH = mh;


        //animation facing left
        attack1 = new ImageIcon("player/Attack1.png").getImage();
        attack2 = new ImageIcon("player/Attack2.png").getImage();
        death = new ImageIcon("player/Death.png").getImage();
        fall = new ImageIcon("player/Fall.png").getImage();
        jump = new ImageIcon("player/Jump.png").getImage();
        run = new ImageIcon("player/Run.png").getImage();
        idle = new ImageIcon("player/Idle.png").getImage();
        takeHit = new ImageIcon("player/Take Hit.png").getImage();
        takeHitWhite = new ImageIcon("player/Take Hit - white silhouette.png").getImage();

        //animation facing right
        attack1F = new ImageIcon("player/Attack1f.png").getImage();
        attack2F = new ImageIcon("player/Attack2f.png").getImage();
        deathF = new ImageIcon("player/Deathf.png").getImage();
        fallF = new ImageIcon("player/Fallf.png").getImage();
        jumpF = new ImageIcon("player/Jumpf.png").getImage();
        runF = new ImageIcon("player/Runf.png").getImage();
        idleF = new ImageIcon("player/Idlef.png").getImage();
        takeHitF = new ImageIcon("player/Take Hitf.png").getImage();
        takeHitWhiteF = new ImageIcon("player/Take Hit - white silhouettef.png").getImage();


        setDefaultValues();
        //getPlayerImage();
    }

    public void setDefaultValues(){
        xCoord = 950;
        yCoord = 420;
        speed = 5;
        spriteW = 200;
        spriteH = 200;
        currentFrame = 0;
        totalFrames = 8;
        animation = "idle";
        direction = "R";
    }

    public void update() {
        animation = "idle";
        if (kH.left && xCoord > -20) {
            xCoord -= speed;
            animation = "run";
            direction = "L";
            System.out.println("left");
            if(kH.jump){
                speed = 12;
            }
            else speed = 5;
        }
        if (kH.right && xCoord < 1720) {
            xCoord += speed;
            animation = "run";
            direction = "R";
            System.out.println("right");
            if(kH.jump){
                speed = 12;
            }
            else speed = 5;
        }
        if (kH.jump && yCoord > 250) {
            animation = "jump";
            yCoord -= 20;
            System.out.println("jump");
        }

        if (yCoord <= 420 && !kH.jump) {
            try {
                Thread.sleep(8);
                yCoord += 8;
            } catch (InterruptedException e) {
                e.printStackTrace();
                // keeps running;
                new Thread((Runnable) this).start();
                System.exit(0);
            }
        }

        if(mH.attacking && !mH.special){
            animation = "normalAtk";
            System.out.println("normalAtk");
        }
        if(mH.special && !mH.attacking) {
            animation = "special";
            System.out.println("Special");
            if (direction.equals("L")) {
                try {
                    Thread.sleep(25);
                    xCoord -= 55;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (direction.equals("R")) {
                try {
                    Thread.sleep(25);
                    xCoord += 55;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void draw(Graphics2D g2) {

        Image image = switch (animation + direction) {
            case "runL" -> runF;
            case "runR" -> run;

            case "jumpL" -> jumpF;
            case "jumpR" -> jump;

            case "idleL" -> idleF;
            case "idleR" -> idle;

            case "normalAtkL" -> attack1F;
            case "normalAtkR" -> attack1;

            case "specialL" -> attack2F;
            case "specialR" -> attack2;

            default -> null;
        };

        int hitBoxX = xCoord + 88;
        int hitBoxY = yCoord + 69;
        g2.setColor(Color.WHITE);
        g2.fillRect(hitBoxX, hitBoxY, 25,55);

        g2.drawImage(image, xCoord, yCoord, null);

    }


    public BufferedImage grabSprite(int x, int y, int w, int h, BufferedImage imageName){
        return imageName.getSubimage(x, y, w, h);
    }

}


