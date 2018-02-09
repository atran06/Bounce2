package entities;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import game.GameObject;
import textures.Textures;

public class Aim extends GameObject {

	public static double x2, y2;
	public static double deg;
	double angle;
	public static boolean go = false;
	
	public Aim(int x, int y, ID id) {
		super(x, y, id);
	}
	public void tick() {
		if(Ball.restart) {
			deg = 0;
		}
		angle = Math.toRadians(deg);
		
		x2 = (x + 100 * Math.cos(angle));
		y2 = (y + 100 * Math.sin(angle));
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(5));
//		g2.draw(new Line2D.Double(x, y + 13, x2, y2 + 13));
		g2.drawImage(Textures.aim[0], (int) x2 - 15, (int) y2 - 3, null);
	}
	public Rectangle getBounds() {
		return null;
	}
}

