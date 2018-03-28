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
		frame.setMinimumSize(new Dimension((int) width, (int) height));
		frame.setMaximumSize(new Dimension((int) width, (int) height));
		
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setUndecorated(true);
		frame.setVisible(true);
//		frame.setSize((int) getScreenWidth(), (int) getScreenHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle(title);
		
		frame.add(game);
		frame.pack();
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
