package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ball extends GameObject{
	
	public static boolean shoot = false;
	public static boolean go = false;
	public static boolean restart = false;
	Handler handler;
	
	public Ball(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public void tick() {
		if(go) {
			velX = (Aim.x2 - x) / 10;
			velY = (Aim.y2 - y) / 10;
			go = false;
		}
		if(shoot) {
			x += velX;
			y += velY;
		}
		if(restart) {
			x = 0;
			y = 360;
			velX = 0;
			velY = 0;
		}
		if(x < 0 || x > 1280 - 25) {
			velX = -velX;
			Game.bounces--;
		}
		if(y < 0 || y > 720 - 60) {
			velY = -velY;
			Game.bounces--;
		}
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.fill(new Ellipse2D.Double(x, y, 25, 25));
	}
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, 25, 25) ;
	}
}
