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

        setDefaultValues();
    }

    // things from inside units
    public void setDefaultValues(){
        this.xCoord = 950;
        this.yCoord = 420;
        this.speed = 5;

        this.spriteName = "Idle";
        this.spriteSheet = loadImage("player/" + spriteName + ".png");
        this.spriteW = 200;
        this.spriteH = 200;
        this.animationDelay = 100;
        this.currentFrame = 0;
        this.totalFrames = 8;
        this.animationTimer = new Timer(animationDelay, e -> {
            updateAnimation();
            gP.repaint();
        });
        this.animationTimer.start();


        this.health = 100;
        this.damage = 15;
        // same as the rect in update, used for gamePanel logic;
        this.playerHitBox = new HitBox(hitBoxX, hitBoxY, 25,55, Color.white);

        this.animation = "idle";
        this.direction = "R";
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

            case "takeHitL" -> "Take Hit - white silhouette";
            case "takeHitR" -> "Take Hit - white silhouettef";

            case "deathL" -> "Deathf";
            case "deathR" -> "Death";

            default -> null;
        };

        totalFrames = switch (animation + direction){
            case "runL", "runR", "idleL", "idleR" -> 8;

            case "jumpL", "jumpR" -> 2;

            case "normalAtkL", "normalAtkR", "specialR", "specialL", "deathL", "deathR" -> 6;

            case "takeHitL", "takeHitR" -> 4;

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


