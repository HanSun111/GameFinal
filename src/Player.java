import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Units{
    public long pressedTimeStart;
    public long pressTimeEnd;
    GamePanel gP;
    KeyHandler kH;
    MouseHandler mH;
    int hitBoxX;
    int hitBoxY;
    HitBox playerHitBox;

    // right side
    Image idle, run, jump, attack1, attack2, fall, death, takeHit, takeHitWhite;
    // left side
    Image idleF, runF, jumpF, attack1F, attack2F, fallF, deathF, takeHitF, takeHitWhiteF;
    Image myHeart, myBrokenHeart;
    //temp
    public Player(GamePanel gp, KeyHandler kh, MouseHandler mh){
        gP = gp;
        kH = kh;
        mH = mh;
        //animation facing left
//        attack1 = new ImageIcon("player/Attack1.png").getImage();
//        attack2 = new ImageIcon("player/Attack2.png").getImage();
//        death = new ImageIcon("player/Death.png").getImage();
//        fall = new ImageIcon("player/Fall.png").getImage();
//        jump = new ImageIcon("player/Jump.png").getImage();
//        run = new ImageIcon("player/Run.png").getImage();
//        idle = new ImageIcon("player/Idle.png").getImage();
//        takeHit = new ImageIcon("player/Take Hit.png").getImage();
//        takeHitWhite = new ImageIcon("player/Take Hit - white silhouette.png").getImage();
//
//        //animation facing right
//        attack1F = new ImageIcon("player/Attack1f.png").getImage();
//        attack2F = new ImageIcon("player/Attack2f.png").getImage();
//        deathF = new ImageIcon("player/Deathf.png").getImage();
//        fallF = new ImageIcon("player/Fallf.png").getImage();
//        jumpF = new ImageIcon("player/Jumpf.png").getImage();
//        runF = new ImageIcon("player/Runf.png").getImage();
//        idleF = new ImageIcon("player/Idlef.png").getImage();
//        takeHitF = new ImageIcon("player/Take Hitf.png").getImage();
//        takeHitWhiteF = new ImageIcon("player/Take Hit - white silhouettef.png").getImage();

        setDefaultValues();
    }

    // things from inside units
    public void setDefaultValues(){
        xCoord = 950;
        yCoord = 420;
        speed = 5;

        spriteName = "Idle";
        spriteSheet = loadImage("player/" + spriteName + ".png");
        spriteW = 200;
        spriteH = 200;
        animationDelay = 100;
        currentFrame = 0;
        totalFrames = 8;
        Timer timer = new Timer(animationDelay, e -> {
            updateAnimation();
            gP.repaint();
        });
        timer.start();


        health = 100;
        damage = 15;
        // same as the rect in update, used for gamePanel logic;
        playerHitBox = new HitBox(hitBoxX, hitBoxY, 25,55, Color.white);

        animation = "idle";
        direction = "R";
    }

    public void update() {
        animation = "idle";
        if (kH.left && xCoord > -20) {
            xCoord -= speed;
            animation = "run";
            spriteName = "Run";
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
            spriteName = "Run";
            direction = "R";
            System.out.println("right");
            if(kH.jump){
                speed = 12;
            }
            else speed = 5;
        }
        if (kH.jump && yCoord > 200) {
            pressedTimeStart = System.currentTimeMillis();
            animation = "jump";
            spriteName = "Jump";
            yCoord -= 20;
            System.out.println("jump");
        }
        if (yCoord <= 420) {
            pressTimeEnd = System.currentTimeMillis();
            long pressedTime = pressTimeEnd - pressedTimeStart;
            if (!kH.jump || pressedTime > 200) {
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
        }

        if(mH.attacking && !mH.special){
            kH.right = false;
            kH.left = false;
            animation = "normalAtk";
            System.out.println("normalAtk");
        }
        if(mH.special && !mH.attacking && xCoord > -20 && xCoord < 1720 && yCoord >= 420) {
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

//        Image image = switch (animation + direction) {
//            case "runL" -> runF;
//            case "runR" -> run;
//
//            case "jumpL" -> jumpF;
//            case "jumpR" -> jump;
//
//            case "idleL" -> idleF;
//            case "idleR" -> idle;
//
//            case "normalAtkL" -> attack1F;
//            case "normalAtkR" -> attack1;
//
//            case "specialL" -> attack2F;
//            case "specialR" -> attack2;
//
//            default -> null;
//        };
//        g2.drawImage(image, xCoord, yCoord, null);

        g2.setColor(Color.WHITE);
        g2.fillRect(hitBoxX, hitBoxY, 25,55);

        spriteName = switch (animation + direction){

            case "runL" -> "Runf";
            case "runR" -> "Run";

            case "jumpL" -> "Jumpf";
            case "jumpR" -> "Jump";

            case "idleL" -> "Idlef";
            case "idleR" -> "Idle";

            case "normalAtkL" -> "Attack1f";
            case "normalAtkR" -> "Attack1";

            case "specialL" -> "Attack2f";
            case "specialR" -> "Attack2";

            default -> null;
        };

        totalFrames = switch (animation + direction){
            case "runL", "runR", "idleL", "idleR" -> 8;

            case "jumpL", "jumpR" -> 2;

            case "normalAtkL", "normalAtkR", "specialR", "specialL" -> 6;

            default -> 8;
        };

        animationDelay = switch (animation + direction){
            case "runL", "runR", "idleL", "idleR" -> 200;

            case "jumpL", "jumpR" -> 200;

            case "normalAtkL", "normalAtkR", "specialR", "specialL" -> 150;

            default -> 100;
        };

        this.spriteSheet = loadImage("player/" + this.spriteName + ".png");
        // Get the current frame's coordinates in the sprite sheet
        int sx = (this.currentFrame % (this.spriteSheet.getWidth() / this.spriteW)) * this.spriteW;
        int sy = (this.currentFrame / (this.spriteSheet.getWidth() / this.spriteW)) * this.spriteH;

        // Draw the current frame
        g2.drawImage(spriteSheet, xCoord, yCoord, spriteSheet.getWidth()/totalFrames + xCoord, spriteSheet.getHeight() + yCoord, sx, sy, sx + spriteW, sy + spriteH, null);
         hitBoxX = this.xCoord + 88;
         hitBoxY = this.yCoord + 69;

    }

}


