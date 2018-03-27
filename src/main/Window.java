package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import game.Game;

public class Window {
	
	Dimension screenSize;
	JFrame frame;
		
	public Window(int width, int height, String title, Game game) {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame = new JFrame();
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setSize((int) getScreenWidth(), (int) getScreenHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle(title);
		
		frame.add(game);
	}
	
	public double getScreenWidth() {
		return screenSize.getWidth();
	}
	
	public double getScreenHeight() {
		return screenSize.getHeight();
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
