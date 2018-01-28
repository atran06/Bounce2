package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	Handler handler;
	public static boolean canPress = true;

	public KeyInput(Handler handler) {
		this.handler = handler;
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
		if (code == KeyEvent.VK_P) {
			if(Game.isRunning) {
				Game.isRunning = false;
			} else if(!Game.isRunning) {
				Game.isRunning = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
