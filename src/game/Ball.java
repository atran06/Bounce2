package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Ball extends GameObject{
	
	public Ball(int x, int y, ID id) {
		super(x, y, id);
		velX = (Aim.x2 - x) / 25;
		velY = (Aim.y2 - y) / 25;
	}
	public void tick() {

		x += velX;
		y += velY;

		if(x < 0 || x > 1280 - 25) {
			//Aim.vX = -Aim.vX;
			velX = -velX;
		}
		if(y < 0 || y > 720 - 45) {
			//Aim.vY = -Aim.vY;
			velY = -velY;
		}
		System.out.println(velY);
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.fill(new Ellipse2D.Double(x, y, 25, 25));
	}
	public Rectangle getBounds() {
		return null;
	}

}
