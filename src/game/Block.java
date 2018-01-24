package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Block extends GameObject {

	public Block(int x, int y, ID id) {
		super(x, y, id);
	}
	public void tick() {
		
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.gray.darker());
		g2.fill(new Rectangle2D.Double(x, y, 32, 32));
		
		g.setColor(Color.white);
		g2.draw(new Rectangle2D.Double(x, y, 32, 32));
		
	}
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, 64, 64);
	}

}