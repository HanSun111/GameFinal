

import javax.swing.*;
import java.awt.*;


public class Player extends Units{
    public long pressedTimeStart;
    public long pressTimeEnd;
    GamePanel gP;
    KeyHandler kH;
    MouseHandler mH;
    int hitBoxX;
    int hitBoxY;
    public int lives, successHit;
    HitBox playerHitBox;
    HitBox normalAtkHitBoxL, specialAtkHitBoxL, normalAtkHitBoxR, specialAtkHitBoxR;
    Image myHeart1, myHeart2, myHeart3, healthyHeart, myBrokenHeart;
    Image chargeStatus1, chargeStatus2, chargeStatus3, fullCharge, needCharge;
    //temp
    public Player(GamePanel gp, KeyHandler kh, MouseHandler mh){
        gP = gp;
        kH = kh;
        mH = mh;

        healthyHeart = new ImageIcon("Heart/MyHeart.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        myBrokenHeart = new ImageIcon("Heart/MyBrokenHeart.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        fullCharge = new ImageIcon("Energy/charged.png").getImage().getScaledInstance(70, 38, Image.SCALE_SMOOTH);
        needCharge = new ImageIcon("Energy/uncharged.png").getImage().getScaledInstance(70, 38, Image.SCALE_SMOOTH);

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
        this.lives = 3;
        successHit = 3;
        myHeart1 = healthyHeart; myHeart2 = healthyHeart; myHeart3 = healthyHeart;
        chargeStatus1 = needCharge; chargeStatus2 = needCharge; chargeStatus3 = needCharge;
        this.isAttacking = false;
        this.isHit = false;
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
        playerHitBox = new HitBox(hitBoxX, hitBoxY, 25,55, Color.white);
        normalAtkHitBoxL = new HitBox(hitBoxX - 55, hitBoxY, 55, 55, Color.darkGray);
        normalAtkHitBoxR = new HitBox(hitBoxX + 80, hitBoxY, 55, 55, Color.darkGray);

        this.animation = "idle";
        this.direction = "R";
    }

    public void update() {
        animation = "idle";
        if (kH.left && xCoord > -20) {
            lives--;
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
            animation = "normalAtk";
            if((currentFrame == 6 || currentFrame == 5) && direction.equals("R")){
                System.out.println("Attacking");
                isAttacking = true;
                if(successHit < 3) {
                    successHit++;
                    System.out.println("successhit");
                }
            }
            // would have been reversed to current frame 1 and 2 but the timer only functions with current animations going up.
            if((currentFrame == 1 || currentFrame == 2) && direction.equals("L")){
                System.out.println("Attacking");
                isAttacking = true;
                if(successHit < 3) {
                    successHit++;
                    System.out.println("successhit");
                }
            }

        }


        if(mH.special && !mH.attacking && xCoord > -20 && xCoord < 1720 && yCoord >= 420 && successHit == 3) {
            damage = 30;
            Timer timer = new Timer(500, e -> {
                successHit = 0;
            });
            animation = "special";
            if (direction.equals("L") && (currentFrame == 6 || currentFrame == 5)) {
                System.out.println("Special");
                isAttacking = true;
                try {
                    Thread.sleep(25);
                    xCoord -= 55;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer.start();

            } else if (direction.equals("R") && (currentFrame == 6 || currentFrame == 5)) {
                System.out.println("Special");
                isAttacking = true;
                try {
                    Thread.sleep(25);
                    xCoord += 55;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer.start();
            }
            timer.stop();
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

            case "jumpL", "jumpR" -> 2;

            case "normalAtkL", "normalAtkR", "specialR", "specialL", "deathL", "deathR" -> 6;

            case "takeHitL", "takeHitR" -> 4;

            default -> 8;
        };

        animationDelay = switch (animation + direction){
            case "runL", "runR", "idleL", "idleR" -> 200;

            case "jumpL", "jumpR" -> 180;

            case "normalAtkL", "normalAtkR", "specialR", "specialL" -> 150;

            default -> 100;
        };

        myHeart3 = switch (lives){
            case 2, 1 -> myBrokenHeart;
            default -> healthyHeart;
        };
        myHeart2 = switch (lives){
            case 1 -> myBrokenHeart;
            default -> healthyHeart;
        };
        myHeart1 = switch (lives){
            case 0 -> myBrokenHeart;
            default -> healthyHeart;
        };

        chargeStatus3 = switch (successHit){
            case 3, 2, 1 -> fullCharge;
            default -> needCharge;
        };
        chargeStatus2 = switch (successHit){
            case 3, 2 -> fullCharge;
            default -> needCharge;
        };
        chargeStatus1 = switch (successHit){
            case 3 -> fullCharge;
            default -> needCharge;
        };

        this.spriteSheet = loadImage("player/" + this.spriteName + ".png");
        // Get the current frame's coordinates in the sprite sheet
        int sx = (this.currentFrame % (this.spriteSheet.getWidth() / this.spriteW)) * this.spriteW;
        int sy = (this.currentFrame / (this.spriteSheet.getWidth() / this.spriteW)) * this.spriteH;

        // Draw the current frame
        g2.drawImage(spriteSheet, xCoord, yCoord, spriteSheet.getWidth()/totalFrames + xCoord, spriteSheet.getHeight() + yCoord, sx, sy, sx + spriteW, sy + spriteH, null);
         hitBoxX = this.xCoord + 88;
         hitBoxY = this.yCoord + 69;

         g2.drawImage(myHeart1, 50, 10, null);
         g2.drawImage(myHeart2, 100, 10, null);
         g2.drawImage(myHeart3, 150, 10, null);

         g2.drawImage(chargeStatus1, 50, 70, null);
         g2.drawImage(chargeStatus2, 100, 70, null);
         g2.drawImage(chargeStatus3, 150, 70, null);


    }

}


