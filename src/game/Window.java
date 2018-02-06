package game;

import javax.swing.JFrame;

public class Window {
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame();
		
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle(title);
		
		frame.add(game);
	}
}
