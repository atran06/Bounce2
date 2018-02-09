package textures;

import java.awt.image.BufferedImage;

import blocks.Block;
import blocks.Block_Break;
import blocks.Block_Moss1;
import blocks.Block_Moss2;
import blocks.Door;
import blocks.Water;
import entities.Ball;
import entities.ID;
import main.Handler;

public class Textures {

	public static SpriteSheet sprite, spBall, spBlock;

	public static BufferedImage level = null, 
			level2 = null, 
			level3 = null, 
			level4 = null,
			level5 =null;
	private BufferedImage spriteSheet = null, spriteBall = null, spriteBlock = null;
	
	public static BufferedImage[][] balls;
	public static BufferedImage[][] blocks;
	public static BufferedImage[] aim;
	public static BufferedImage[] nums;
	
	static Handler handler;
	
	public Textures(Handler handler) {
		
		this.handler = handler;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.imageLoader("/Levels/Level1.png");
		level2 = loader.imageLoader("/Levels/Level2.png");
		level3 = loader.imageLoader("/Levels/Level3.png");
		level4 = loader.imageLoader("/Levels/Level4.png");
		level5 = loader.imageLoader("/Levels/Level5.png");
		spriteSheet = loader.imageLoader("/Sprites/SpriteSheet.png");
		spriteBall = loader.imageLoader("/Sprites/Sprite_Ball.png");
		spriteBlock = loader.imageLoader("/Sprites/Sprite_Block.png");
		
		sprite = new SpriteSheet(spriteSheet);
		spBall = new SpriteSheet(spriteBall);
		spBlock = new SpriteSheet(spriteBlock);
		
		balls = new BufferedImage[5][6];
		blocks = new BufferedImage[6][3];
		nums = new BufferedImage[10];
		aim = new BufferedImage[1];
		
		loadTextures();
		loadLevel(level);
	}
	public void loadTextures() {
		balls[0][0] = spBall.getImg(Ball.ball, 1, 32, 32);
		balls[0][1] = spBall.getImg(Ball.ball, 2, 32, 32);
		balls[0][2] = spBall.getImg(Ball.ball, 3, 32, 32);
		balls[0][3] = spBall.getImg(Ball.ball, 4, 32, 32);
		balls[0][4] = spBall.getImg(Ball.ball, 5, 32, 32);
		balls[0][5] = spBall.getImg(Ball.ball, 6, 32, 32);
		
		blocks[0][0] = spBlock.getImg(1, 1, 32, 32);
		blocks[1][0] = spBlock.getImg(2, 1, 32, 32);
		blocks[2][0] = spBlock.getImg(3, 1, 32, 32);
		blocks[3][0] = spBlock.getImg(4, 1, 32, 32);
		blocks[4][0] = spBlock.getImg(1, 2, 32, 32);
		blocks[4][1] = spBlock.getImg(2, 2, 32, 32);
		blocks[4][2] = spBlock.getImg(3, 2, 32, 32);
		blocks[5][0] = spBlock.getImg(1, 3, 32, 32);
		
		nums[0] = spBlock.getImg(10, 5, 32, 32);
		nums[1] = spBlock.getImg(1, 5, 32, 32);
		nums[2] = spBlock.getImg(2, 5, 32, 32);
		nums[3] = spBlock.getImg(3, 5, 32, 32);
		nums[4] = spBlock.getImg(4, 5, 32, 32);
		nums[5] = spBlock.getImg(5, 5, 32, 32);
		nums[6] = spBlock.getImg(6, 5, 32, 32);
		nums[7] = spBlock.getImg(7, 5, 32, 32);
		nums[8] = spBlock.getImg(8, 5, 32, 32);
		nums[9] = spBlock.getImg(9, 5, 32, 32);
		
		aim[0] = spBall.getImg(10, 6, 32, 32);
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
