package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

/**
 * Created by aydin on 4/19/17.
 */
public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x,y, width, height);
    }
}
