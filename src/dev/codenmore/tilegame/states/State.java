package dev.codenmore.tilegame.states;

import dev.codenmore.tilegame.Game;

import java.awt.*;

/**
 * Created by aydin on 4/19/17.
 */
public abstract class State {

    private static State currentState = null;

    protected Game game;
    public State(Game game){
        this.game = game;
    }

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }

    //CLASS

    public abstract void tick();
    public abstract void render(Graphics g);
}
