import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    public boolean attacking, special;
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int pressed = e.getButton();

        if(pressed == MouseEvent.BUTTON1){
            attacking = true;
        }
        if(pressed == MouseEvent.BUTTON3){
            special = true;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int pressed = e.getButton();

        if(pressed == KeyEvent.VK_A){
            attacking = false;
        }
        if(pressed == KeyEvent.VK_D){
            special = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
