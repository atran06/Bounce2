package game;

import java.awt.BasicStroke;
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
		collision();

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
			x = 32;
			y = 360;
			velX = 0;
			velY = 0;
		}
//		if(x < 0 || x > 1280 - 25) {
//			velX = -velX;
//			Game.bounces--;
//		}
//		if(y < 0 || y > 720 - 60) {
//			velY = -velY;
//			Game.bounces--;
//		}
	}
	public void collision() {
		for(int i = 0; i < handler.list.size(); i++) {
			GameObject temp = handler.list.get(i);
			
			if(temp.getId() == ID.block) {
				if(getBounds().intersects(temp.getBounds())) {
					velY = -velY;
				}
			} 
			if(temp.getId() == ID.block) {
				if(getBoundsBottom().intersects(temp.getBounds())) {
					velY = -velY;
				}
			} 
			if(temp.getId() == ID.block) {
				if(getBoundsLeft().intersects(temp.getBounds())) {
					velX = -velX;
				}
			} 
			if(temp.getId() == ID.block) {
				if(getBoundsRight().intersects(temp.getBounds())) {
					velX = -velX;
				}
			}
		}
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.cyan);
		g2.fill(new Ellipse2D.Double(x, y, 25, 25));

		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.red);
		g2.draw(getBoundsBottom());
		g2.setColor(Color.blue);
		g2.draw(getBounds());
		g2.setColor(Color.BLACK);
		g2.draw(getBoundsLeft());
		g2.draw(getBoundsRight());
	}
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x + 6, y, 25 - 12, 25 / 2) ;
	}
	public Rectangle2D getBoundsBottom() {
		return new Rectangle2D.Double(x + 6, y + 25 / 2, 25 - 12, 25 / 2) ;
	}
	public Rectangle2D getBoundsLeft() {
		return new Rectangle2D.Double(x, y + 3, 25 - 22, 25 - 6) ;
	}
	public Rectangle2D getBoundsRight() {
		return new Rectangle2D.Double(x + 22, y + 3, 25 - 22, 25 - 6) ;
	}
}
