package game;

import javax.swing.JFrame;

public class Window {

	public static int width, height;
	
	public Window(int width, int height, String title) {
		JFrame frame = new JFrame();
		Game game = new Game();
		Handler handler = new Handler();
		
		this.width = width;
		this.height = height;
		
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle(title);
		
		frame.add(game);
		frame.addMouseListener(new MouseInput());
		frame.addKeyListener(new KeyInput(handler));
	}
}
