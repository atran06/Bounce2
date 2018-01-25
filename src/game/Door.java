package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Door extends GameObject {

	public Door(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.black);
		g2.fill(new Rectangle2D.Double(x, y, 32, 32));
		
	}

	@Override
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, 32, 32);
	}

}
