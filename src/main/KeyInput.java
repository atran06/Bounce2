package main;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import blocks.Block;
import entities.Aim;
import entities.Ball;
import game.AudioPlayer;
import game.Game;

public class KeyInput implements KeyListener {

	Handler handler;
	Window window;
	Game game;
	public static boolean canPress = true;
	private AudioPlayer clack;

	public KeyInput(Handler handler, Window window, Game game) {
		this.game = game;
		this.window = window;
		this.handler = handler;
		clack = new AudioPlayer("/Music and Sounds/claves.wav", false);
		clack.setVolume(.1f);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_0) {
			if(window.getFrame().getExtendedState() == JFrame.MAXIMIZED_BOTH) {
				window.getFrame().dispose();
				
				window.getFrame().getContentPane().setPreferredSize(new Dimension((int) window.getWidth(), (int) window.getHeight()));
				window.getFrame().setMinimumSize(new Dimension((int) window.getWidth(), (int) window.getHeight()));
				window.getFrame().setMaximumSize(new Dimension((int) window.getWidth(), (int) window.getHeight()));
				
				window.getFrame().setLocation((int) ((window.getScreenWidth() / 2) - (window.getWidth() / 2)),
						(int) ((window.getScreenHeight() / 2) - window.getHeight() / 2));
				window.getFrame().setExtendedState(JFrame.NORMAL);
				window.getFrame().setUndecorated(false);
				window.getFrame().setVisible(true);
				
				window.getFrame().pack();
			} else if(window.getFrame().getExtendedState() == JFrame.NORMAL) {
				window.getFrame().dispose();
				
				window.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				window.getFrame().setSize((int) window.getScreenWidth(), (int) window.getScreenHeight());
				window.getFrame().setUndecorated(true);
				window.getFrame().setVisible(true);
			}
		}
		if (canPress) {
			if (code == KeyEvent.VK_SPACE) {
				Ball.go = true;
				Ball.shoot = true;
				canPress = false;
				Ball.running = true;
				Ball.restart = false;
			}
		}
		if (code == KeyEvent.VK_UP) {
			Aim.deg -= 5;
			Ball.restart = false;
		}
		if (code == KeyEvent.VK_DOWN) {
			Aim.deg += 5;
			Ball.restart = false;
		}
		if (code == KeyEvent.VK_R) {
			Ball.restart = true;
			canPress = true;
			Ball.changeY = false;
			if (Game.llvl == 1) {
				Game.bounces = 1;
			} else if (Game.llvl == 2) {
				Game.bounces = 3;
			} else if (Game.llvl == 3) {
				Game.bounces = 3;
			} else if (Game.llvl == 4) {
				Game.bounces = 3;
			}
		}
		if(Ball.running) {
			if (code == KeyEvent.VK_W) {
				clack.play();
				Ball.changeY = true;
				Game.bounces--;
			}
			if (code == KeyEvent.VK_A) {
				clack.play();
				Ball.changeX = true;
				Game.bounces--;
			}			
		}
//		if(code == KeyEvent.VK_0) {
//			Game.bg.play();
//		}
//		if(code == KeyEvent.VK_9) {
//			Game.bg.stop();
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
