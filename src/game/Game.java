package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	Thread thread;

	public static int bounces = 5;
	private boolean isRunning;

	private static Handler handler;
	public static STATE state = STATE.game;
	
	private BufferedImage level = null;

	public Game() {
		Window win = new Window(1280, 720, "Bounce", this);

		isRunning = true;
		thread = new Thread(this);
		thread.start();

		handler = new Handler();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.imageLoader("/resources/Blok_Scene_HP.png");
		
		loadLevel(level);
		
		handler.addObject(new Aim(45, 355, ID.aim));
		handler.addObject(new Ball(32, 355, ID.ball, handler));
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

	public void render() {
		BufferStrategy strat = this.getBufferStrategy();
		if (strat == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = strat.getDrawGraphics();

		g.setColor(Color.gray);
		g.fillRect(0, 0, 1280, 720);

		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(bounces), 100, 100);

		handler.paintComponent(g);

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
				
				if(red == 255) {
					handler.addObject(new Block(xx*32, yy*32, ID.block));
				}
			}
		}
	}
}
