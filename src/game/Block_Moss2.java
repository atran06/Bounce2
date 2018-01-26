package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Block_Moss2 extends GameObject {

	Image img;
	
	public Block_Moss2(int x, int y, ID id) {
		super(x, y, id);
		
		loadImage();
	}
	public void tick() {
		
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
//		g.setColor(Color.gray.darker());
//		g2.fill(new Rectangle2D.Double(x, y, 32, 32));
//		
//		g.setColor(Color.white);
//		g2.draw(new Rectangle2D.Double(x, y, 32, 32));
	
		g2.drawImage(img, (int) x, (int) y, null);
	}
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, 32, 32);
	}
	public void loadImage() {
		img = new ImageIcon(getClass().getResource("/resources/Block_Moss2.png")).getImage();
	}
}
