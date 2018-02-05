package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.sun.corba.se.spi.orbutil.fsm.State;

import game.Game.STATE;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static Thread thread;

	public static int bounces = 1;
	public static int llvl = 1;
	public static boolean isRunning;

	private static Handler handler;
	private Textures tex;
	private Image menu, background, settings, help;

	public static enum STATE {
		game, menu, setting, win, help
	};

	public static STATE state = STATE.menu;

	public Game() {
		new Window(1287, 720, "Bounce", this);

		this.addMouseListener(new MouseInput());
		this.addKeyListener(new KeyInput(handler));

		isRunning = true;
		thread = new Thread(this);
		thread.start();
		
		tex = new Textures();
		handler = new Handler();
		
		handler.addObject(new Aim(50, 360, ID.aim));
		handler.addObject(new Ball(34, 357, ID.ball, handler));
		
		loadImage();
	}

	public void loadImage() {
		menu = new ImageIcon(getClass().getResource("/resources/Menu.png")).getImage();
		background = new ImageIcon(getClass().getResource("/resources/Bg.png")).getImage();
		settings = new ImageIcon(getClass().getResource("/resources/Settings.png")).getImage();
		help = new ImageIcon(getClass().getResource("/resources/Help.png")).getImage();
	}

	public void render() {
		BufferStrategy strat = this.getBufferStrategy();
		if (strat == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = strat.getDrawGraphics();
		if (state == STATE.menu) {
			g.drawImage(menu, 0, 0, null);
			if(state != STATE.menu) {
				g.dispose();
			}
		} else if (state == STATE.game) {
			g.drawImage(background, 0, 0, null);

			handler.paintComponent(g);

			if (bounces == 1) {
				g.drawImage(tex.nums[1], 32, 648, null);
			} else if (bounces == 2) {
				g.drawImage(tex.nums[2], 32, 648, null);
			} else if (bounces == 3) {
				g.drawImage(tex.nums[3], 32, 648, null);
			} else if (bounces == 4) {
				g.drawImage(tex.nums[4], 32, 648, null);
			} else if (bounces == 5) {
				g.drawImage(tex.nums[5], 32, 648, null);
			} else if (bounces == 0) {
				g.drawImage(tex.nums[0], 32, 648, null);
			}
			if (llvl == 1) {
				g.drawImage(tex.nums[1], 32, 648, null);
			} else if (llvl == 2) {
				g.drawImage(tex.nums[2], 32, 648, null);
			} else if (llvl == 3) {
				g.drawImage(tex.nums[3], 32, 648, null);
			} else if (llvl == 4) {
				g.drawImage(tex.nums[4], 32, 648, null);
			} else if (llvl == 5) {
				g.drawImage(tex.nums[5], 32, 648, null);
			} else if (llvl == 0) {
				g.drawImage(tex.nums[0], 32, 648, null);
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
		}
		g.dispose();
		strat.show();
	}

	private void tick() {
		if (state == STATE.game) {
			handler.tick();
		}
		if (bounces < 0) {
			Ball.restart = true;
			KeyInput.canPress = true;
			if (llvl == 1) {
				bounces = 1;
			} else if (llvl == 2) {
				bounces = 3;
			} else if (llvl == 3) {
				bounces = 3;
			} else if (llvl == 4) {
				bounces = 3;
			} else if (llvl == 5) {
				if(Ball.broken = true) {
					bounces = 0;
					Ball.broken = false;
				} else {
					bounces = 2;					
				}
			}
		}
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
}
