package blocks;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.Animation;
import game.GameObject;
import game.ID;
import game.Textures;

public class Water extends GameObject {

	private Animation animation;
	
	public Water(int x, int y, ID id) {
		super(x, y, id);
		animation = new Animation(10, Textures.blocks[4][0],
				Textures.blocks[4][1],
				Textures.blocks[4][2]);
	}
	public void tick() {
		animation.runAnimation();
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		 g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
		 animation.drawAnimation(g2, (int) x, (int) y, 1, 1);
	}	
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, 32, 16);
	}
}
