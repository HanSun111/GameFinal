import java.awt.*;
import java.awt.event.KeyEvent;

public class Box extends Rectangle {
    Color color;
    Box(int x, int y, int width, int height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public void keyPressed(KeyEvent e){
        // sprite uses repaint for keylistener to animate it
        if(e.getKeyCode() == KeyEvent.VK_A){
            x = x - 10;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            x = x + 10;
        }

    }
    public void draw(Graphics g){
        g.setColor(this.color);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
