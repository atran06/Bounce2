package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Block_Moss2 extends GameObject {
	
	public Block_Moss2(int x, int y, ID id) {
		super(x, y, id);
	}
	public void tick() {
		
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
	
		g2.drawImage(Game.sprite.getImg(3, 1, 32, 32), (int) x, (int) y, null);
	}
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, 32, 32);
	}
}
