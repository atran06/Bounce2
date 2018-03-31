package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import game.Game;

public class Window {
	
	private double width, height;
	
	Dimension screenSize;
	JFrame frame;
		
	public Window(double width, double height, String title, Game game) {
		this.width = width;
		this.height = height;
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame = new JFrame();
		
		frame.getContentPane().setPreferredSize(new Dimension((int) width, (int) height));

		frame.setExtendedState(JFrame.NORMAL);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation((int) ((screenSize.getWidth() / 2) - (width / 2)), 
				(int) ((screenSize.getHeight() / 2) - (height / 2)));
		frame.setResizable(false);
		frame.setTitle(title);
		frame.setVisible(true);
		
		frame.add(game);
		frame.pack();
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public double getFrameWidth() {
		return frame.getContentPane().getWidth();
	}
	
	public double getFrameHeight() {
		return frame.getContentPane().getHeight();
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
