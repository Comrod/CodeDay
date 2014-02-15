package platformer.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import platformer.Window.Window;

public class DumbEnemy extends Entity {

	public DumbEnemy(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void run() {
		while(Window.started) {
				handleDeath();
				handleGravity();
				dumbPatrol();
				collisionBox.setBounds(getX(), getY(), getWidth(), getHeight());
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}

	public void dumbPatrol() {
		for(int i = 0; i < Window.platforms.size(); i++)
			if(collisionBox.intersects(Window.platforms.get(i).collisionBox)) {
				if(getX() <= Window.platforms.get(i).collisionBox.x) {
					dumbWalkDirection = 1;
				} if(getX()+getWidth() >= Window.platforms.get(i).collisionBox.x+Window.platforms.get(i).collisionBox.width) {
					dumbWalkDirection = -1;
				}
				setX(getX()+dumbWalkDirection);
			}
	}

	public void handleDeath() {
		if(dead) {
			setY(getY()+20);
			setHeight(getHeight()-20);
			dead = false;
			setY(getY()+5);
			done = true;
		}
	}

	public void handleGravity() {
		for(int i = 0; i < Window.platforms.size(); i++) {
			if(collisionBox.intersects(Window.platforms.get(i).collisionBox)) {
				if(collisionBox.y+collisionBox.height <= Window.platforms.get(i).collisionBox.y+1) {
					colliding = true;
					return;
				}
			} else if(!collisionBox.intersects(Window.platforms.get(i).collisionBox)) {
				colliding = false;
			}
		}
		if(!colliding) {
			setY(getY()+1);	
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect(getX(), getY()-1, getWidth(), getHeight());

		if(Window.gui.collisionLines) {
			g.setColor(Color.orange);
			g.drawRect(collisionBox.x, collisionBox.y-1, collisionBox.width, collisionBox.height);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public boolean colliding;
	boolean done = false;
	int dumbWalkDirection = -1;
}
