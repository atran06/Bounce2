package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_SPACE) {
			Ball.shoot = true;
		}
		if(code == KeyEvent.VK_UP) {
			Aim.deg--;
		}
		if(code == KeyEvent.VK_DOWN) {
			Aim.deg++;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
