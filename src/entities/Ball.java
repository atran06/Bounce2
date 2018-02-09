package entities;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.Animation;
import game.AudioPlayer;
import game.Game;
import game.GameObject;
import game.ID;
import game.Textures;
import main.Handler;
import main.KeyInput;

public class Ball extends GameObject{
	
	public static boolean shoot = false;
	public static boolean changeY = false, changeX = false;
	public static boolean go = false;
	public static boolean restart = false;
	public static boolean running = false;
	public static boolean broken = false;
	
	Handler handler;
	Textures texture;
	private Animation animation;
	private Animation animationBack;
	private AudioPlayer collide;
	private AudioPlayer door;

	private int size = 32;
	private int xOrg, yOrg;
	public static int ball = 2;
	
	public Ball(int x, int y, ID id, Handler handler, Textures tex) {
		super(x, y, id);
		this.handler = handler;
		this.texture = tex;
		this.xOrg = x;
		this.yOrg = y;
		animation = new Animation(1, Textures.balls[0][1],
				Textures.balls[0][2],
				Textures.balls[0][3],
				Textures.balls[0][4],
				Textures.balls[0][5]);
		animationBack = new Animation(1, Textures.balls[0][5],
				Textures.balls[0][4],
				Textures.balls[0][3],
				Textures.balls[0][2],
				Textures.balls[0][1]);
		
		collide = new AudioPlayer("/Music and Sounds/thud.wav", false);
		door = new AudioPlayer("/Music and Sounds/nextlevel.wav", false);
		door.setVolume(.1f);
	}
	public void tick() {
		collision();

		if(go) {
			velX = ((Aim.x2 - 16) - x) / 10;
			velY = ((Aim.y2 - 3) - y) / 10;
			go = false;
		}
		if(shoot) {
			if(changeY) {
				velY = -velY;
				changeY = false;
			}
			if(changeX) {
				velX = -velX;
				changeX = false;
			}
			x += velX;
			y += velY;
		}
		if(restart) {
			x = xOrg;
			y = yOrg;
			velX = 0;
			velY = 0;
			running = false;
			if(Game.llvl == 1) {
				Game.bounces = 1;
			} else if(Game.llvl == 2) {
				Game.bounces = 5;
			} else if(Game.llvl == 3) {
				Game.bounces = 2;
			} else if(Game.llvl == 4) {
				Game.bounces = 3;
			} else if(Game.llvl == 5) {
				Game.bounces = 2;
			}
		}
		if(velX > 0) {
			animation.runAnimation();			
		} else if(velX < 0) {
			animationBack.runAnimation();
		}
	}
	public void collision() {
		for(int i = 0; i < handler.list.size(); i++) {
			GameObject temp = handler.list.get(i);
			
			if(temp.getId() == ID.door) {
				if(getBoundsBottom().intersects(temp.getBounds())) {
					door.play();
					Game.bounces++;
					Game.llvl++;
					handler.switchLvl();
				}
			}
			else if(temp.getId() == ID.block) {
  				if(getBoundsLeft().intersects(temp.getBounds())) {
  					collide.play();
					x = temp.getX() + 32;
					velX = -velX;
					restart = true;
					KeyInput.canPress = true;
				}
			} 
			else if(temp.getId() == ID.block) {
				if(getBoundsRight().intersects(temp.getBounds())) {
					collide.play();
					x = temp.getX() - 32;
					velX = -velX;
					restart = true;
					KeyInput.canPress = true;
				}
			}
			else if(temp.getId() == ID.block) {
				if(getBounds().intersects(temp.getBounds())) {
					collide.play();
					y = temp.getY() + 32;
					velY = -velY;
					restart = true;
					KeyInput.canPress = true;
				}
			} 
			if(temp.getId() == ID.block) {
				if(getBoundsBottom().intersects(temp.getBounds())) {
					collide.play();
					y = temp.getY() - 32;
					velY = -velY;
					restart = true;
					KeyInput.canPress = true;
				}
			} 
			else if(temp.getId() == ID.blockBreak) {
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
		g2.drawImage(Textures.balls[0][0], (int) x, (int) y, null);
		
		if(velX != 0) {
			if(velX > 0) {
				animation.drawAnimation(g, (int) x, (int) y, 1, 1);
			} else if(velX < 0) {
				animationBack.drawAnimation(g, (int) x, (int) y, 1, 1);		
			} else {
				animation.drawAnimation(g, (int) x, (int) y, 1, 1);
			}			
		} else {
			g.drawImage(Textures.balls[0][0], (int) x, (int) y, null);
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
