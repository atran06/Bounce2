package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	Handler handler;
	private boolean canPress = true;
	
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
			}
		}
		if(code == KeyEvent.VK_UP) {
			Aim.deg -= 5;
		}
		if(code == KeyEvent.VK_DOWN) {
			Aim.deg += 5;
		}
		if(code == KeyEvent.VK_R) {
			Game.restart = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
