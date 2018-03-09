package blocks;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import entities.Ball;
import entities.ID;
import game.Animation;
import game.GameObject;
import textures.Textures;

public class Block extends GameObject {
	
	private Animation animationHit;
	private Timer timer;
	private int seconds = 0;
	
	public Block(int x, int y, ID id) {
		super(x, y, id);
		
		animationHit = new Animation(1, Textures.balls[0][5],
				Textures.balls[0][4],
				Textures.balls[0][3],
				Textures.balls[0][2],
				Textures.balls[0][1]);
		
		timer = new Timer();
		count();
	}
	public void tick() {
		
		if(!Ball.hit) {
			seconds = 0;
			System.out.println(seconds);
		} else if(Ball.hit) {
			animationHit.runAnimation();
		} 
	}
	public void count() {
		timer.schedule(new TimerTask() {
			public void run(){
				seconds++;
				
				if(seconds > 0) {
					seconds = 0;
					Ball.hit = false;
				}
			}
		}, 1000, 1000);
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g2.drawImage(Textures.blocks[0][0], (int) x, (int) y, null);
		
		if(Ball.hit) {
			animationHit.drawAnimation(g, Ball.hitX, Ball.hitY, 1, 1);
		}
	}	
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, 32, 32);
	}
}

