package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	Handler handler;
	public static boolean canPress = true;
	private AudioPlayer clack;

	public KeyInput(Handler handler) {
		this.handler = handler;
		clack = new AudioPlayer("/resources/Music and Sounds/claves.wav", false);
		clack.setVolume(.5f);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
