package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (mx >= 515 && mx <= 770) {
			if (my >= 430 && my <= 700) {
				Game.state = Game.STATE.game;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

}
