package blocks;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game.GameObject;
import game.ID;
import game.Textures;

public class Block extends GameObject {
	
	public Block(int x, int y, ID id) {
		super(x, y, id);
	}
	public void tick() {
		
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g2.drawImage(Textures.blocks[0][0], (int) x, (int) y, null);			
	}	
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, 32, 32);
	}
}
