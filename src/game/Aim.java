package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Aim extends GameObject {

	public static double x2, y2;
	public static double vX, vY;
	
	public Aim(int x, int y, ID id) {
		super(x, y, id);
		
		double angle = Math.toRadians(0);
		
		x2 = (x + 100 * Math.cos(angle));
		y2 = (y + 100 * Math.sin(angle));
		
		vX = x2;
		vY = y2;
		
		System.out.println(x2 + " " + y2);
	}
	public void tick() {
		
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.draw(new Line2D.Double(x, y, x2, y2));
		
		g2.fill(new Ellipse2D.Double(x, y, 10, 10));
	}
	public Rectangle getBounds() {
		return null;
	}

}
