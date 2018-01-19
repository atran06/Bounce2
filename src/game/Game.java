package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	Thread thread;
	
	public static int bounces = 5;
	private boolean isRunning;

	private Handler handler;
	public static STATE state = STATE.game;

	public Game() {
		Window win = new Window(1280, 720, "Bounce", this);
		
		isRunning = true;
		thread = new Thread(this);
		thread.start();

		handler = new Handler();
		handler.addObject(new Aim(13, 360, ID.aim));
		handler.addObject(new Ball(0, 360, ID.ball, handler));
	}
	public void run() {
		/////// Game Loop ////////
		long beforeTime = System.nanoTime();
		double ticks = 60.0;
		double optimal = 1000000000 / ticks;
		double lastFPSTime = 0;
		long fps = 0;
		double delta = 0;
		
		while(isRunning) {
			long currentTime = System.nanoTime();
			delta += (currentTime - beforeTime) / optimal;
			long updateLength = currentTime - beforeTime;
			beforeTime = currentTime;
			
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			lastFPSTime += updateLength;
			fps++;
			
			/////Updates the FPS/////
			if(lastFPSTime >= 1000000000) {
				System.out.println("FPS: " + fps);
				fps = 0;
				lastFPSTime = 0;
			}
			
			render();
		}
	}
	public void render() {
		BufferStrategy strat = this.getBufferStrategy();
		if(strat == null) {
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
		
		if(bounces <= 0) {
			Ball.restart = true;
			KeyInput.canPress = true;
			Game.bounces = 5;
		}
	}
}
