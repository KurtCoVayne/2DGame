package threads_test;

import java.awt.image.BufferedImage;

/**
 * Assets
 */
public class Assets {
    public static BufferedImage player1,player2,player3;

    private static final int width = 32, height = 32;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/test.png"));
        player1 = sheet.crop(0, 0, width, height);
        player2 = sheet.crop(width, 0, width, height);
        player3 = sheet.crop(width*2, 0, width, height);
    }
}