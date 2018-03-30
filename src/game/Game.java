package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;

import entities.Aim;
import entities.Ball;
import entities.ID;
import main.Handler;
import main.KeyInput;
import main.MouseInput;
import main.Window;
import textures.Textures;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static Thread thread;

	public static float volume = .5f;
	public static int bounces = 1;
	public static int llvl = 1;
	public static boolean isRunning;
	private int seconds = 0;
	private double scaleWidth, scaleHeight;
	
	private Window window;
	private static Handler handler;
	private static Textures tex;
	public static AudioPlayer bg;
	
	private Image menu, background, settings, help, load;

	public static enum STATE {
		game, menu, setting, win, help, load, end
	};

	public static STATE state = STATE.menu;

	public Game() {
		window = new Window(1280, 720, "Bounce", this);
		
		this.addMouseListener(new MouseInput(this));
		this.addKeyListener(new KeyInput(handler, window, this));

		bg = new AudioPlayer("/Music and Sounds/bg2.wav", true);
		bg.play();

		isRunning = true;
		thread = new Thread(this);
		thread.start();
		
		handler = new Handler(tex);
		tex = new Textures(handler);
		
		handler.addObject(new Aim(50, 360, ID.aim));
		handler.addObject(new Ball(34, 357, ID.ball, handler, tex));
		
		loadImage();
	}

	public void loadImage() {
		load = new ImageIcon(getClass().getResource("/Images/Load.gif")).getImage();
		menu = new ImageIcon(getClass().getResource("/Images/Menu.png")).getImage();
		background = new ImageIcon(getClass().getResource("/Images/Bg.png")).getImage();
		settings = new ImageIcon(getClass().getResource("/Images/Settings.png")).getImage();
		help = new ImageIcon(getClass().getResource("/Images/Help.png")).getImage();
	}
	
	public void render() {
		BufferStrategy strat = this.getBufferStrategy();
		if (strat == null) {
			this.createBufferStrategy(4);
			return;
		}
		Graphics g = strat.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;
		
		g2.scale(scaleWidth, scaleHeight);
		
		if(state == STATE.load) {
			g.setColor(new Color(24, 19, 16));
			g.fillRect(0, 0, 1287, 720);
			g.drawImage(load, 380, 100, null);
			
		} else if (state == STATE.menu) {
			g.drawImage(menu, 0, 0, null);
			if(state != STATE.menu) {
				g.dispose();
			}
		} else if (state == STATE.game) {
			g.drawImage(background, 0, 0, null);

			handler.paintComponent(g);

			if (bounces == 1) {
				g.drawImage(Textures.nums[1], 32, 648, null);
			} else if (bounces == 2) {
				g.drawImage(Textures.nums[2], 32, 648, null);
			} else if (bounces == 3) {
				g.drawImage(Textures.nums[3], 32, 648, null);
			} else if (bounces == 4) {
				g.drawImage(Textures.nums[4], 32, 648, null);
			} else if (bounces == 5) {
				g.drawImage(Textures.nums[5], 32, 648, null);
			} else if (bounces == 0) {
				g.drawImage(Textures.nums[0], 32, 648, null);
			}
			if (llvl == 1) {
				g.drawImage(Textures.nums[1], 1215, 648, null);
			} else if (llvl == 2) {
				g.drawImage(Textures.nums[2], 1215, 648, null);
			} else if (llvl == 3) {
				g.drawImage(Textures.nums[3], 1215, 648, null);
			} else if (llvl == 4) {
				g.drawImage(Textures.nums[4], 1215, 648, null);
			} else if (llvl == 5) {
				g.drawImage(Textures.nums[5], 1215, 648, null);
			} else if (llvl == 0) {
				g.drawImage(Textures.nums[0], 1215, 648, null);
			}
		} else if(state == STATE.setting) {
			g.drawImage(settings, 0, 0, null);
			if(state != STATE.setting) {
				g.dispose();
			}
		} else if(state == STATE.help) {
			g.drawImage(help, 0, 0, null);
			if(state != STATE.help) {
				g.dispose();
			}
		} else if(state == STATE.end) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 1287, 720);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.drawString("You Win!", 500, 300);
		}
		g.dispose();
		strat.show();
	}
	
	private void tick() {
		scaleWidth = window.getFrameWidth() / 1280;
		scaleHeight = window.getFrameHeight() / 720;
		
		if(state == STATE.load) {
			if(seconds == 5) {
				state = STATE.menu;
			}
		}
		if (state == STATE.game) {
			handler.tick();
		}
		if (bounces < 0) {
			Ball.restart = true;
			KeyInput.canPress = true;
			Ball.changeY = false;
		}
		bg.setVolume(volume);
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
				if(seconds < 5) {
					seconds++;
					System.out.println(seconds);					
				}
				System.out.println("FPS: " + fps);
				fps = 0;
				lastFPSTime = 0;
			}

			render();
		}
	}

	public double getScaleWidth() {
		return scaleWidth;
	}
	
	public void setScaleWidth(double scaleWidth) {
		this.scaleWidth = scaleWidth;
	}
	
	public double getScaleHeight() {
		return scaleHeight;
	}
	
	public void setScaleHeight(double scaleHeight) {
		this.scaleHeight = scaleHeight;
	}
}
