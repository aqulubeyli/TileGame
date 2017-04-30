package dev.codenmore.tilegame.tilegame;

import dev.codenmore.tilegame.gfx.Assets;

/**
 * Created by aydin on 4/30/17.
 */
public class RockTile extends Tile {

    public RockTile(int id){
        super(Assets.stone, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
