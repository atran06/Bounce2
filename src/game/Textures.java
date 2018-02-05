package game;

import java.awt.image.BufferedImage;

public class Textures {

	public static SpriteSheet sprite;

	public static BufferedImage level = null, 
			level2 = null, 
			level3 = null, 
			level4 = null,
			level5 =null;
	private BufferedImage spriteSheet = null;
	
	static Handler handler;
	
	public Textures(Handler handler) {
		
		this.handler = handler;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.imageLoader("/resources/Level1.png");
		level2 = loader.imageLoader("/resources/Level2.png");
		level3 = loader.imageLoader("/resources/Level3.png");
		level4 = loader.imageLoader("/resources/Level4.png");
		level5 = loader.imageLoader("/resources/Level5.png");
		spriteSheet = loader.imageLoader("/resources/SpriteSheet.png");
		
		sprite = new SpriteSheet(spriteSheet);

		loadLevel(level);
	}
	
	public static void loadLevel(BufferedImage image) {

		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && blue == 0 && green == 0) {
					handler.addObject(new Block(xx * 32, yy * 32, ID.block));
				}
				if (blue == 255 && red == 0 && green == 0) {
					handler.addObject(new Door(xx * 32, yy * 32, ID.door));
				}
				if (blue == 0 && red == 0 && green == 255) {
					handler.addObject(new Block_Moss1(xx * 32, yy * 32, ID.block));
				}
				if (blue == 0 && red == 255 && green == 255) {
					handler.addObject(new Block_Moss2(xx * 32, yy * 32, ID.block));
				}
				if (blue == 255 && red == 0 && green == 255) {
					handler.addObject(new Block_Break(xx * 32, yy * 32, ID.blockBreak));
				}
				if (blue == 255 && red == 255 && green == 0) {
					handler.addObject(new Water(xx * 32, yy * 32, ID.water));
				}
			}
		}
	}
}
