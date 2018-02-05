package game;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Water extends GameObject {

	public Water(int x, int y, ID id) {
		super(x, y, id);
	}
	public void tick() {
		
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		 g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
		 g2.fill(new Rectangle2D.Double(x, y, 32, 32));
//		 g2.drawImage(Game.sprite.getImg(6, 1, 32, 32), (int) x, (int) y, null);
	}	
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, 32, 16);
	}
}
