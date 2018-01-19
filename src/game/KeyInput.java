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
		
		if(canPress) {
			if(code == KeyEvent.VK_SPACE) {
				Ball.go = true;
				Ball.shoot = true;
				canPress = false;
				Ball.restart = false;
			}
		}
		if(code == KeyEvent.VK_UP) {
			Aim.deg -= 5;
			Ball.restart = false;
		}
		if(code == KeyEvent.VK_DOWN) {
			Aim.deg += 5;
			Ball.restart = false;
		}
		if(code == KeyEvent.VK_R) {
			Ball.restart = true;
			canPress = true;
			Game.bounces = 5;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
