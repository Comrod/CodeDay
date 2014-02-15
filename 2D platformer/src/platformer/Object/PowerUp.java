package platformer.Object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import platformer.Window.Window;

public class PowerUp implements Runnable {

	public PowerUp(int x, int y) {
		this.x = x;
		this.y = y;
		collisionBox.setBounds(x, y, 25, 25);
	}
	
	public void run() {
		while(Window.started) {
			for(int i = 0; i < 150; i++) {
				if(!colorUpDown && Window.started) {
					red--;
				} else if(colorUpDown && Window.started) {
					red++;
				}
				try {
					if(Window.started)
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(colorUpDown)
				colorUpDown = false;
			else 
				colorUpDown = true;
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(new Color(red, 0, 0));
		g.fillOval(x, y, 25, 25);
		
		if(Window.gui.collisionLines) {
			g.setColor(Color.magenta);
			g.drawOval(collisionBox.x, collisionBox.y, 25, 25);
		}
	}
	
	public int x, y;
	public int red = 250;
	public boolean colorUpDown = false;
	Color color, outline;
	public static Random ran = new Random();
	public Rectangle collisionBox = new Rectangle();
}
