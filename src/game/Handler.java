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
		
		switch(Game.llvl) {
		
		case 2:
			Textures.loadLevel(Textures.level2);
			Game.bounces += 3;
			addObject(new Aim(50, 360, ID.aim));
			addObject(new Ball(34, 357, ID.ball, this));
			break;
		
		case 3:
			Textures.loadLevel(Textures.level3);
			Game.bounces += 2;
			addObject(new Aim(50, 360, ID.aim));
			addObject(new Ball(34, 357, ID.ball, this));
			break;
			
		case 4:
			Textures.loadLevel(Textures.level4);
			Game.bounces += 3;
			addObject(new Aim(50, 360, ID.aim));
			addObject(new Ball(34, 357, ID.ball, this));
			break;
			
		case 5:
			Textures.loadLevel(Textures.level5);
//			Game.loadLevel(Game.level2);
			Game.bounces += 1;
			addObject(new Aim(630, 550, ID.aim));
			addObject(new Ball(614, 547, ID.ball, this));
			break;
		case 6: 
			Textures.loadLevel(Textures.level);
		}
	}
}
