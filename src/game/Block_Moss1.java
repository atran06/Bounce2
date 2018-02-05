package game;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Block_Moss1 extends GameObject {
	
	Textures tex;
	
	public Block_Moss1(int x, int y, ID id) {
		super(x, y, id);
		
		tex = new Textures();
	}
	public void tick() {
		
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
	
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		g2.drawImage(tex.blocks[1], (int) x, (int) y, null);
	}
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, 32, 32);
	}
}
