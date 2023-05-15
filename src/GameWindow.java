import java.awt.*;

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
}
