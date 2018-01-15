package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject{

	public Ball(int x, int y, ID id) {
		super(x, y, id);
		velX = 4;
		velY = 4;
	}
	public void tick() {
		if(x < 0 || x > 1280 - 25) {
			velX = -velX;
		}
		if(y < 0 || y > 720 - 45) {
			velY = -velY;
		}

		x += velX;
		y += velY;
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, 25, 25);
	}
	public Rectangle getBounds() {
		return null;
	}

}
