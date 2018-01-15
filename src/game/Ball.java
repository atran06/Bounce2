package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject{

	public Ball(int x, int y, ID id) {
		super(x, y, id);
	}
	public void tick() {
		velX = 5;
		velY = 5;
		
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
