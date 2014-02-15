package platformer.Object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import platformer.Window.Window;

public class Coin {

	public Coin(int x, int y) {
		this.x = x;
		this.y = y;
		collisionBox.setBounds(x, y, 25, 25);
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.cyan);
		g.fillOval(x, y, 25, 25);
		
		if(Window.gui.collisionLines) {
			g.setColor(Color.blue);
			g.drawOval(collisionBox.x, collisionBox.y, 25, 25);
		}
	}
	
	public int x, y;
	public Rectangle collisionBox = new Rectangle();
}
