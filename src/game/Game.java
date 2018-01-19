package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	Timer timer = new Timer(5, this);
	
	public static int bounces = 5;

	private Handler handler;
	public static STATE state = STATE.game;

	public Game() {
		timer.start();

		handler = new Handler();
		handler.addObject(new Aim(13, 360, ID.aim));
		handler.addObject(new Ball(0, 360, ID.ball, handler));
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, 1280, 720);
		
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(bounces), 100, 100);

		handler.paintComponent(g);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();

		handler.tick();
	}
}
