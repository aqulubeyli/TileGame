package dev.codenmore.tilegame;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.SpriteSheet;
import dev.codenmore.tilegame.input.KeyManager;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.MenuState;
import dev.codenmore.tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by aydin on 4/15/17.
 */

// Episode 18 ende
public class Game implements Runnable{

    private Display display;
    private Thread thread;
    private Graphics g;
    private BufferStrategy bs;

    //Sheet
    private BufferedImage test;
    private SpriteSheet sheet;

    //State
    private State gameState;
    private State menuState;

    //Input
    private KeyManager keyManager;

    //For Local
    private String title;
    private int width, height;
    private boolean running = false;

    //RUN VARIBLES
    private final long PAUSE_TIME = 1;
    private final float FPS = 60.0f;
    private final long  SECOND = 1_000_000_000l;
    private final float UPDATE_INTERVAL = SECOND/FPS;


    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameState = new GameState(this);
        menuState = new MenuState(this);
        State.setState(gameState);
    }

    private void tick(){

        keyManager.tick();

        if(State.getState() != null){
            State.getState().tick();
        }

    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();

        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        // clear screen
        g.clearRect(0,0,width, height);

        // Draw
//        g.setColor(Color.BLACK);
//        g.fillRect(0,0,width,height);

        if(State.getState() != null){
            State.getState().render(g);
        }
        bs.show();
        g.dispose();

    }

    ///////////////////////////////////////////////////////////
    @Override
    public void run() {

        int fps =0;
        int tick = 0;
        int render_step_one = 0;
        int render_step_two = 0;
        int count_pause = 0;
        float delta = 0;
        long count = 0;

        init();

        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            long eL = now - lastTime;           //elpased time
            count+=eL;
            lastTime = now;
            boolean shouldRender = false;        // Heleki rendering olunmayib

            delta+= (eL/UPDATE_INTERVAL);

            while(delta >= 1){
                tick();
                tick++;
                delta--;

                if(shouldRender){
                    render_step_one++;
                } else{
                    shouldRender = true;
                    render_step_two++;

                } // End else

            } // end while

            // rendere ehtiyac varsa?
            if(shouldRender){
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(PAUSE_TIME);
                    count_pause++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // End else

            if(count >= 1000000000){
                System.out.println("|||||FPS:"+fps+"||||UPDATE:"+tick+"|||RENDER_one:"+render_step_one+"||RENDER_two:"+render_step_two+"|PAUSE:"+count_pause);
                fps = 0;
                count = 0;
                tick = 0;
                render_step_one = 0;
                render_step_two = 0;
                count_pause = 0;
            }
        }
        stop();
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }


    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running){
            return;
        }
        running = false;

        thread = new Thread(this);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
