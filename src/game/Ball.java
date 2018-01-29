package game;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Ball extends GameObject{
	
	public static boolean shoot = false;
	public static boolean go = false;
	public static boolean restart = false;
	public static boolean broken = false;
	
	Handler handler;

	private int size = 32;
	private int xOrg, yOrg;
	public static int ball = 2;
	
	public Ball(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.xOrg = x;
		this.yOrg = y;
	}
	public void tick() {
		collision();

		if(go) {
			velX = ((Aim.x2 - 16) - x) / 10;
			velY = ((Aim.y2 - 3) - y) / 10;
			go = false;
		}
		if(shoot) {
			x += velX;
			y += velY;
		}
		if(restart) {
			x = xOrg;
			y = yOrg;
			velX = 0;
			velY = 0;
		}
	}
	public void collision() {
		for(int i = 0; i < handler.list.size(); i++) {
			GameObject temp = handler.list.get(i);
			
			if(temp.getId() == ID.block) {
  				if(getBoundsLeft().intersects(temp.getBounds())) {
					x = temp.getX() + 32;
					velX = -velX;
					Game.bounces--;
				}
			} 
			if(temp.getId() == ID.block) {
				if(getBoundsRight().intersects(temp.getBounds())) {
					x = temp.getX() - 32;
					velX = -velX;
					Game.bounces--;
				}
			}
			if(temp.getId() == ID.block) {
				if(getBounds().intersects(temp.getBounds())) {
					y = temp.getY() + 32;
					velY = -velY;
					Game.bounces--;
				}
			} 
			if(temp.getId() == ID.block) {
				if(getBoundsBottom().intersects(temp.getBounds())) {
					y = temp.getY() - 32;
					velY = -velY;
					Game.bounces--;
				}
			} 
			if(temp.getId() == ID.door) {
				if(getBoundsBottom().intersects(temp.getBounds())) {
					Game.bounces++;
					Game.llvl++;
					handler.switchLvl();
				}
			}
			if(temp.getId() == ID.blockBreak) {
				if(getBoundsBottom().intersects(temp.getBounds())) {
					Game.bounces--;
					velY = -velY;
					broken = true;
					handler.removeObject(temp);
				}
			}
		}
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		 
		if(ball == 1) {
			g2.drawImage(Game.sprite.getImg(1, 3, 32, 32), (int) x, (int) y, null);			
		} else if(ball == 2) {
			g2.drawImage(Game.sprite.getImg(2, 3, 32, 32), (int) x, (int) y, null);
		} else if(ball == 3) {
			g2.drawImage(Game.sprite.getImg(2, 3, 32, 32), (int) x, (int) y, null);
		} else if(ball == 4) {
			g2.drawImage(Game.sprite.getImg(4, 3, 32, 32), (int) x, (int) y, null);
		} else if(ball == 5) {
			g2.drawImage(Game.sprite.getImg(5, 3, 32, 32), (int) x, (int) y, null);
		} else if(ball == 6) {
			g2.drawImage(Game.sprite.getImg(6, 3, 32, 32), (int) x, (int) y, null);
		}

//		g2.setStroke(new BasicStroke(1));
//		g2.setColor(Color.red);
//		g2.draw(getBoundsBottom());
//		g2.setColor(Color.blue);
//		g2.draw(getBounds());
//		g2.setColor(Color.BLACK);
//		g2.draw(getBoundsLeft());
//		g2.setColor(Color.white);
//		g2.draw(getBoundsRight());
	}
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x + 7, y, size - 14, size / 2);
	}
	public Rectangle2D getBoundsBottom() {
		return new Rectangle2D.Double(x + 7, y + size / 2, size - 14, size / 2);
	}
	public Rectangle2D getBoundsLeft() {
		return new Rectangle2D.Double(x, y + 9, size - 26, size - 18);
	}
	public Rectangle2D getBoundsRight() {
		return new Rectangle2D.Double(x + 32 - 6, y + 9, size - 26, size - 18);
	}
}
