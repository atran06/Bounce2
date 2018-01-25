package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> list = new LinkedList<>();
	
	public Handler() {
		
	}
	public void paintComponent(Graphics g) {
		for(int i = 0; i < list.size(); i++) {
			GameObject tempObject = list.get(i);
			
			tempObject.paintComponent(g);
		}
	}
	public void tick() {
		for(int i = 0; i < list.size(); i++) {
			GameObject tempObject = list.get(i);
			
			tempObject.tick();
		}
	}
	public void removeObject(GameObject tempObject) {
		list.remove(tempObject);
	}
	public void addObject(GameObject tempObject) {
		list.add(tempObject);
	}
	private void clearLvl() {
		list.clear();
	}
	public void switchLvl() {
		clearLvl();
		Ball.restart = true;
		KeyInput.canPress = true;
		Game.bounces = 5;
		
		switch(Game.llvl) {
		
		case 1:
			Game.loadLevel(Game.level2);
			Game.llvl++;
			addObject(new Aim(45, 360, ID.aim));
			addObject(new Ball(34, 360, ID.ball, this));
			break;
		
		case 2:
			Game.loadLevel(Game.level3);
			Game.llvl++;
			addObject(new Aim(45, 360, ID.aim));
			addObject(new Ball(34, 360, ID.ball, this));
			break;
		}
	}
}
