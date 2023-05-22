import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class GameWindow extends Canvas implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Thread thread;
    private boolean running = false;
    GameFrame g = new GameFrame();

// game logic start
public void start(){
    running = true;
    // create thread
    thread = new Thread(this);
    thread.start();
}

//stops game logic
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
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta+=(now - lastTime)/ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            Render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    //updates window
    public void tick(){

    }

    //paints window(draws frame)
    public void Render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs ==null){
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0, this.getWidth(), this.getHeight());

        bs.show();
        g.dispose();
    }
}
