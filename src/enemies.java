import java.awt.*;

public class enemies extends Units{
    public int hitBoxX;
    public int hitBoxY;
    public enemies(){

        setDefaultValues();
    }

    public void setDefaultValues(){
        xCoord = 950;
        yCoord = 420;
        speed = 4;
        spriteW = 200;
        spriteH = 200;
        hitBox = new Rectangle(hitBoxX, hitBoxY, 25,55);
        currentFrame = 0;
        totalFrames = 8;
        animation = "idle";

        //int directionDecider = (int) (Math.random() * 2);
        if((int) (Math.random() * 2) == 0) {
            direction = "R";
        }
        else direction = "L";
    }
    // from 1 to 3
    public void enemyType(int num){
        if(num == 1){

        }
    }

    public void update(){

    }

    public void draw(Graphics2D g2) {

    }
}
