package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	Thread thread;

	public static int bounces = 1;
	public static int llvl = 1;
	private boolean isRunning;

	private static Handler handler;
	public static STATE state = STATE.game;

	public static BufferedImage level = null, level2 = null, level3 = null, level4 = null;
	private Image menu, hud, background;
	private Image zero, one, two, three, four, five;

	public Game() {
		Window win = new Window(1287, 720, "Bounce", this);

		isRunning = true;
		thread = new Thread(this);
		thread.start();

		handler = new Handler();


		if (state == STATE.game) {

			BufferedImageLoader loader = new BufferedImageLoader();
			level = loader.imageLoader("/resources/Level1.png");
			level2 = loader.imageLoader("/resources/Level2.png");
			level3 = loader.imageLoader("/resources/Level3.png");
			level4 = loader.imageLoader("/resources/Level4.png");

			loadLevel(level);

			handler.addObject(new Aim(50, 360, ID.aim));
			handler.addObject(new Ball(34, 357, ID.ball, handler));
		}
		loadImage();
	}

	public void run() {
		/////// Game Loop ////////
		long beforeTime = System.nanoTime();
		double ticks = 60.0;
		double optimal = 1000000000 / ticks;
		double lastFPSTime = 0;
		long fps = 0;
		double delta = 0;

		while (isRunning) {
			long currentTime = System.nanoTime();
			delta += (currentTime - beforeTime) / optimal;
			long updateLength = currentTime - beforeTime;
			beforeTime = currentTime;

			while (delta >= 1) {
				tick();
				delta--;
			}

			lastFPSTime += updateLength;
			fps++;

			///// Updates the FPS/////
			if (lastFPSTime >= 1000000000) {
				System.out.println("FPS: " + fps);
				fps = 0;
				lastFPSTime = 0;
			}

			render();
		}
	}

	public void loadImage() {
		if(state == STATE.menu) {
			menu = new ImageIcon(getClass().getResource("/resources/Blok_Menu.png")).getImage();
		}
		if(state == STATE.game) {
			background = new ImageIcon(getClass().getResource("/resources/Bg.png")).getImage();
			
			one = new ImageIcon(getClass().getResource("/resources/1.png")).getImage();
			two = new ImageIcon(getClass().getResource("/resources/2.png")).getImage();
			three = new ImageIcon(getClass().getResource("/resources/3.png")).getImage();
			four = new ImageIcon(getClass().getResource("/resources/4.png")).getImage();
			five = new ImageIcon(getClass().getResource("/resources/5.png")).getImage();
			zero = new ImageIcon(getClass().getResource("/resources/0.png")).getImage();
		}
	}
	public void render() {
		BufferStrategy strat = this.getBufferStrategy();
		if (strat == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = strat.getDrawGraphics();

		if (state == STATE.game) {
//			g.setColor(Color.gray);
//			g.fillRect(0, 0, 1287, 720);
			g.drawImage(background, 0, 0, null);
			
			handler.paintComponent(g);
			
			if(bounces == 1) {
				g.drawImage(one, 32, 639, null);
			} else if(bounces == 2) {
				g.drawImage(two, 32, 639, null);
			} else if(bounces == 3) {
				g.drawImage(three, 32, 639, null);
			} else if(bounces == 4) {
				g.drawImage(four, 32, 639, null);
			} else if(bounces == 5) {
				g.drawImage(five, 32, 639, null);
			} else if(bounces == 0) {
				g.drawImage(zero, 32, 639, null);
			}
			if(llvl == 1) {
				g.drawImage(one, 1200, 639, null);
			} else if(llvl == 2) {
				g.drawImage(two, 1200, 639, null);
			} else if(llvl== 3) {
				g.drawImage(three, 1200, 639, null);
			} else if(llvl== 4) {
				g.drawImage(four, 1200, 639, null);
			} else if(llvl == 5) {
				g.drawImage(five, 1200, 639, null);
			} else if(llvl == 0) {
				g.drawImage(zero, 1200, 639, null);
			}
		}
		if (state == STATE.menu) {
			g.drawImage(menu, 0, 0, null);
		}
		g.dispose();
		strat.show();
	}

	private void tick() {
		handler.tick();

		if (bounces < 0) {
			Ball.restart = true;
			KeyInput.canPress = true;
			if(llvl == 1) {
				bounces = 1;				
			} else if(llvl == 2) {
				bounces = 3;
			} else if(llvl == 3) {
				bounces = 3;
			} else if(llvl == 4) {
				bounces = 3;
			}
		}
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
			}
		}
	}
}
