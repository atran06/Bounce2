package game;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import entities.ID;

public abstract class GameObject {

	protected double x, y;
	protected double velX, velY;
	protected ID id;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	public abstract void tick();
	public abstract void paintComponent(Graphics g);
	public abstract Rectangle2D getBounds();
	
	public double getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public double getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public double getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
}
