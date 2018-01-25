package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.ImageIcon;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	Thread thread;

	public static int bounces = 5;
	public static int llvl = 1;
	private boolean isRunning;

	private static Handler handler;
	public static STATE state = STATE.game;

	public static BufferedImage level = null, level2 = null, level3 = null;
	private Image menu, hud;
	private Image one, two, three, four, five;

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

			loadLevel(level);

			handler.addObject(new Aim(45, 360, ID.aim));
			handler.addObject(new Ball(34, 360, ID.ball, handler));
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
			hud = new ImageIcon(getClass().getResource("/resources/HUD.png")).getImage();
			
			one = new ImageIcon(getClass().getResource("/resources/1.png")).getImage();
			two = new ImageIcon(getClass().getResource("/resources/2.png")).getImage();
			three = new ImageIcon(getClass().getResource("/resources/3.png")).getImage();
			four = new ImageIcon(getClass().getResource("/resources/4.png")).getImage();
			five = new ImageIcon(getClass().getResource("/resources/5.png")).getImage();
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
			g.setColor(Color.gray);
			g.fillRect(0, 0, 1287, 720);

			handler.paintComponent(g);
			
			if(bounces == 1) {
				g.drawImage(one, 210, 641, null);
			} else if(bounces == 2) {
				g.drawImage(two, 210, 641, null);
			} else if(bounces == 3) {
				g.drawImage(three, 210, 641, null);
			} else if(bounces == 4) {
				g.drawImage(four, 210, 641, null);
			} else if(bounces == 5) {
				g.drawImage(five, 210, 641, null);
			}
			g.drawImage(hud, 0, 0, null);
		}
		if (state == STATE.menu) {
			g.drawImage(menu, 0, 0, null);
		}

		g.dispose();
		strat.show();
	}

	private void tick() {
		handler.tick();

		if (bounces <= 0) {
			Ball.restart = true;
			KeyInput.canPress = true;
			Game.bounces = 5;
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

				if (red == 255) {
					handler.addObject(new Block(xx * 32, yy * 32, ID.block));
				}
				if (blue == 255) {
					handler.addObject(new Door(xx * 32, yy * 32, ID.door));
				}
			}
		}
	}
}
