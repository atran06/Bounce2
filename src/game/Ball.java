package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject{
	
	public Ball(int x, int y, ID id) {
		super(x, y, id);
		
	}
	public void tick() {
		velX = (MouseInput.mx - 25) / 100;
		velY = (MouseInput.my - 25) / 100;

		x += velX;
		y += velY;

		if(x < 0 || x > 1280 - 25) {
			//velX = -velX;
			MouseInput.mx = - MouseInput.mx;
		}
		if(y < 0 || y > 720 - 45) {
			//velY = -velY;
			MouseInput.my = - MouseInput.my;
		}
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, 25, 25);
	}
	public Rectangle getBounds() {
		return null;
	}

}
