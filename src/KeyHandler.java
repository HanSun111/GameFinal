import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean left, right, jump;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int pressed = e.getKeyCode();

        if(pressed == KeyEvent.VK_A){
            left = true;
        }
        if(pressed == KeyEvent.VK_D){
            right = true;
        }
        if(pressed == KeyEvent.VK_SPACE){
            jump = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int pressed = e.getKeyCode();

        if(pressed == KeyEvent.VK_A){
            left = false;
        }
        if(pressed == KeyEvent.VK_D){
            right = false;
        }
        if(pressed == KeyEvent.VK_SPACE){
            jump = false;
        }
    }
}