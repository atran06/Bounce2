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
	
	private Handler handler;
	
	public Game() {
		timer.start();
		
		handler = new Handler();
		handler.addObject(new Ball(0, 0, ID.ball));
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, 1280, 720);
		
		handler.paintComponent(g);
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
		handler.tick();
	}
}
