package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Aim extends GameObject {

	private double x2, y2;
	
	public Aim(int x, int y, ID id) {
		super(x, y, id);
		
		double angle = Math.toRadians(180);
		
		x2 = (x + 100 * Math.cos(angle));
		y2 = (y + 100 * Math.sin(angle));
	}
	public void tick() {
		
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.draw(new Line2D.Double(x, y, x2, y2));
		
		g.setColor(Color.BLACK);
		g.fillOval(x, y, 5, 5);
	}
	public Rectangle getBounds() {
		return null;
	}

}
