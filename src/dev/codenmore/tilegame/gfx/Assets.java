package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

/**
 * Created by aydin on 4/19/17.
 */
public class Assets {

    private static final int width= 32, height = 32;

    public static BufferedImage player, dirt, grass, stone;

    public static void init(){

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

        player = sheet.crop(0,0, width, height);
        dirt = sheet.crop(width,0,width, height);
        grass = sheet.crop(width*2, 0, width, height );
        stone = sheet.crop(width*3, 0, width, height);


    }
}
