package dev.codenmore.tilegame;

import dev.codenmore.tilegame.display.Display;

/**
 * Created by aydin on 4/15/17.
 */
public class Launcher {
    public static void main(String[] args){
        Game game = new Game("Test", 640, 440);
        game.start();
    }
}
