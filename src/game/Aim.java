package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Aim extends GameObject {

	public static double x2, y2;
	public static double deg = 10;
	double angle;
	public static boolean go = false;
	
	public Aim(int x, int y, ID id) {
		super(x, y, id);
				
		angle = Math.toRadians(deg);
		
		x2 = (x + 100 * Math.cos(angle));
		y2 = (y + 100 * Math.sin(angle));
	}
	public void tick() {
		deg += 10;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(5));
		g2.draw(new Line2D.Double(x, 373, x2, y2 + 13));
	}
	public Rectangle getBounds() {
		return null;
	}

}
