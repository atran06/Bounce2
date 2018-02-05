package game;

import java.awt.image.BufferedImage;

public class Textures {

	private BufferedImage spriteSheetBall = null, spriteSheetBlock = null;
	
	public BufferedImage[] nums;
	public BufferedImage[][] ball;
	public BufferedImage[] blocks;
	
	private static Handler handler;
	
	public static BufferedImage level = null,
			level2 = null,
			level3 = null,
			level4 = null,
			level5 = null;
	
	private SpriteSheet sheetBall, sheetBlock;
	
	public Textures() {
		
		nums = new BufferedImage[10];
		ball = new BufferedImage[6][6];
		blocks = new BufferedImage[6]; 
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		level = loader.imageLoader("/resources/Level1.png");
		level2 = loader.imageLoader("/resources/Level2.png");
		level3 = loader.imageLoader("/resources/Level3.png");
		level4 = loader.imageLoader("/resources/Level4.png");
		level5 = loader.imageLoader("/resources/Level5.png");
		
		handler = new Handler();


		spriteSheetBall = loader.imageLoader("/resources/Sprite_Ball.png");
		spriteSheetBlock = loader.imageLoader("/resources/Sprite_Block_Nums.png");
		
		sheetBall = new SpriteSheet(spriteSheetBall);
		sheetBlock = new SpriteSheet(spriteSheetBlock);

		loadLevel(level);
		loadTextures();
	}
	public void loadTextures() {
		
		nums[0] = sheetBlock.getImg(10, 5, 32, 32);
		nums[1] = sheetBlock.getImg(1, 5, 32, 32);
		nums[2] = sheetBlock.getImg(2, 5, 32, 32);
		nums[3] = sheetBlock.getImg(3, 5, 32, 32);
		nums[4] = sheetBlock.getImg(4, 5, 32, 32);
		nums[5] = sheetBlock.getImg(5, 5, 32, 32);
		nums[6] = sheetBlock.getImg(6, 5, 32, 32);
		nums[7] = sheetBlock.getImg(7, 5, 32, 32);
		nums[8] = sheetBlock.getImg(8, 5, 32, 32);
		nums[9] = sheetBlock.getImg(9, 5, 32, 32);
		
		ball[0][0] = sheetBall.getImg(1, 1, 32, 32);
		ball[0][1] = sheetBall.getImg(1, 2, 32, 32);
		ball[0][2] = sheetBall.getImg(1, 3, 32, 32);
		ball[0][3] = sheetBall.getImg(1, 4, 32, 32);
		ball[0][4] = sheetBall.getImg(1, 5, 32, 32);
		ball[0][5] = sheetBall.getImg(1, 6, 32, 32);
		
		blocks[0] = sheetBlock.getImg(1, 1, 32, 32);
		blocks[1] = sheetBlock.getImg(2, 1, 32, 32);
		blocks[2] = sheetBlock.getImg(3, 1, 32, 32);
		blocks[3] = sheetBlock.getImg(4, 1, 32, 32);
		blocks[4] = sheetBlock.getImg(1, 2, 32, 32);
		blocks[5] = sheetBlock.getImg(1, 3, 32, 32);
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
