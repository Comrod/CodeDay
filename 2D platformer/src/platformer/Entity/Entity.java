package platformer.Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import platformer.Object.Bullets;

public abstract class Entity implements Runnable, KeyListener {

	/** Defines what an entity is */
	public Entity(int x, int y, int width, int height) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	/** Every entity will have their own thread to process stuff */
	@Override
	public abstract void run();
	public abstract void render(Graphics2D g);
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyTyped(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private int x, y, width, height;
	public boolean dead;
	public boolean colliding;
	public byte coins;
	public int points;
	public ArrayList<Bullets> bullets = new ArrayList<Bullets>();
	public byte facing = 1;
	//public byte health;
	public Rectangle collisionBox = new Rectangle();
}
