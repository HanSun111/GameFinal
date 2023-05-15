import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameWindow extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    private Thread thread;
    private boolean running = false;
    GameFrame g = new GameFrame();


public void start(){
    running = true;
    thread = new Thread(this);
    thread.start();
}
public void stop(){
    running = false;
    try {
        thread.join();
    } catch (InterruptedException e){
        e.printStackTrace();
    }
}
    @Override
    public void run() {
    while(running){
        System.out.println("TESTING");
    }
    }
    public void Render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs ==null){
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect();
        g.dispose();
    }
}
