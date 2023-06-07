import javax.swing.*;
import java.awt.*;

public class Enemies extends Units{
    GamePanel gP;
    public int hitBoxX;
    public int hitBoxY;
    public HitBox enemyHitBox;
    public String animation;
    Image attack, death, idle, takeHit, walk;
    Image attackF, deathF, idleF, takeHitF, walkF;
    public Enemies(GamePanel gP){
        this.gP = gP;
        animation = "walk";
        enemyHitBox = new HitBox(hitBoxX, hitBoxY, 25, 55, Color.black);
        attack = new ImageIcon("Enemies/Skelly/Attack.png").getImage();
        death = new ImageIcon("Enemies/Skelly/Death.png").getImage();
        walk = new ImageIcon("Enemies/Skelly/Walk.png").getImage();
        idle = new ImageIcon("Enemies/Skelly/Idle.png").getImage();
        takeHit = new ImageIcon("Enemies/Skelly/Take Hit.png").getImage();

        attackF = new ImageIcon("Enemies/Skelly/Attackf.png").getImage();
        deathF = new ImageIcon("Enemies/Skelly/Deathf.png").getImage();
        walkF = new ImageIcon("Enemies/Skelly/Walkf.png").getImage();
        idleF = new ImageIcon("Enemies/Skelly/Idlef.png").getImage();
        takeHitF = new ImageIcon("Enemies/Skelly/Take Hitf.png").getImage();

        setDefaultValues();
    }

    public void setDefaultValues() {
        this.yCoord = 445;
        this.speed = 4;
        this.health = 45;
        this.damage = 20;
        this.isAttacking = false;
        this.isHit = false;
        this.animation = "idle";
        if ((int) (Math.random() * 2) == 0) {
            this.direction = "L";
            this.xCoord = 1700;
        } else {
            this.direction = "R";
            this.xCoord = 200;
        }

        this.spriteName = "Idle";
        this.spriteSheet = loadImage("player/" + spriteName + ".png");
        this.spriteW = 150;
        this.spriteH = 150;
        this.animationDelay = 150;
        this.currentFrame = 0;
        this.totalFrames = 4;
        Timer timer = new Timer(animationDelay, e -> {
            updateAnimation();
            gP.repaint();
        });
        timer.start();
    }

    public void update(){
        isAttacking = false;
        boolean intersecting = gP.player.playerHitBox.intersects(enemyHitBox);
        if(intersecting){
            this.animation = "atk";
        }
        if(this.xCoord < gP.player.xCoord){
            this.direction = "R";
            this.animation = "walk";
            this.xCoord += this.speed;
        }
        if(this.xCoord > gP.player.xCoord + 50){
            this.direction = "L";
            this.animation = "walk";
            this.xCoord -= this.speed;
        }

        if(spriteName.equals("atkR") || spriteName.equals("atkL")){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(this.currentFrame == 7 || this.currentFrame == 8){
                isAttacking = true;
            }
            if(health <= 0){
                animation = "death";
            }
        }


    }

    public void draw(Graphics2D g2) {
        spriteName = switch (animation + direction){
            case "walkL" -> "Walkf";
            case "walkR" -> "Walk";

            case "takeHitL" -> "Take Hitf";
            case "takeHitR" -> "Take Hit";

            case "idleL" -> "Idlef";
            case "idleR" -> "Idle";

            case "atkL" -> "Attackf";
            case "atkR" -> "Attack";

            case "deathL" -> "Deathf";
            case "deathR" -> "Death";

            default -> null;
        };

        this.totalFrames = switch (animation + direction){
            case "walkL", "walkR", "takeHitR", "takeHitL", "idleR", "idleL", "DeathL", "DeathR" -> 4;

            case "atkL", "atkR" -> 8;

            default -> 4;
        };
        g2.setColor(Color.cyan);
        hitBoxX = xCoord + 55;
        hitBoxY = yCoord + 48;
        g2.fillRect(hitBoxX, hitBoxY, 35, 55);

        spriteSheet = loadImage("Skelly/" + spriteName + ".png");
        // Get the current frame's coordinates in the sprite sheet
        int sx = (currentFrame % (spriteSheet.getWidth() / spriteW)) * spriteW;
        int sy = (currentFrame / (spriteSheet.getWidth() / spriteW)) * spriteH;

        g2.drawImage(spriteSheet, xCoord, yCoord, spriteSheet.getWidth()/totalFrames + xCoord, spriteSheet.getHeight() + yCoord, sx, sy, sx + spriteW, sy + spriteH, null);

        if(animation.equals("death") && currentFrame == totalFrames){
            g2.clearRect(100, 100, 30, 30);
        }
    }
}
