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
        yCoord = 445;
        speed = 8;
        animation = "idle";
        if ((int) (Math.random() * 2) == 0) {
            direction = "L";
            xCoord = 1700;
        } else {
            direction = "R";
            xCoord = 200;
        }

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
    }

    public void update(){

    }

    public void draw(Graphics2D g2) {
        Image image = switch (animation + direction){
            case "walkL" -> walkF;
            case "walkR" -> walk;

            case "takeHitL" -> takeHitF;
            case "takeHitR" -> takeHit;

            case "idleL" -> idleF;
            case "idleR" -> idle;

            case "atkL" -> attackF;
            case "atkR" -> attack;

            case "DeathL" -> deathF;
            case "DeathR" -> death;

            default -> null;
        };
        g2.setColor(Color.cyan);
        hitBoxX = xCoord + 55;
        hitBoxY = yCoord + 48;
        g2.fillRect(hitBoxX, hitBoxY, 35, 55);
        g2.drawImage(image, xCoord, yCoord, null);

    }
}
