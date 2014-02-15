package platformer.Object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import platformer.Window.Window;

public class Platform implements Runnable {

	public Platform(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public Platform(int x,int y, int width, int height,boolean outofbounds){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.outofbounds = outofbounds;
	}

	public Platform(int x, int y, int width, int height, boolean eventPlatform, int action) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.eventPlatform = eventPlatform;
		this.action = action;
	}

	public Platform(int x, int y, int width, int height, boolean eventPlatform, int action, boolean visible) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.eventPlatform = eventPlatform;
		this.action = action;
		this.visible = visible;
	}


	public Platform(int x, int y, int width, int height, String moving, int tox) {
		this.startX = x;
		this.moving = moving;
		this.tox = tox;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void run() {
		while(Window.started) {
			if(moving != null)
				handleMove(tox);
			collisionBox.setBounds(x, y, width, height);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void handleMove(int tox) {
		if(x >= tox) {
			direction = "left";
			slideDirection = -1;
		} if(x <= startX) {
			direction = "right";
			slideDirection = 1;
		}
		x+=slideDirection;
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
	}
}

	public void render(Graphics2D g) {
		if(visible) {
			g.setColor(Color.magenta);
			g.fillRect(x, y, width, height);

			if(Window.gui.collisionLines) {
				g.setColor(Color.green);
				g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
			}
		}
	}	

	int startX;
	int x, y, width, height;
	int tox;
	boolean visible = true;
	public int action;
	public boolean eventPlatform = false,outofbounds = false;
	public String direction;
	public int slideDirection = 1;
	public String moving = null;
	public Rectangle collisionBox = new Rectangle();
}
