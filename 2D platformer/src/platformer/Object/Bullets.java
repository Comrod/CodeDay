package platformer.Object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import platformer.Entity.Player;
import platformer.Window.Window;

public class Bullets implements Runnable {
	public int x,y;
	public Rectangle collisionBox = new Rectangle();

	public void Render(Graphics2D g){
		if(alive) {
			g.setColor(Color.RED);
			g.fillOval(x, y, 10, 10);

			g.setColor(Color.blue);
			g.drawOval(collisionBox.x, collisionBox.y, 10, 10);
		}

	}
	public Bullets(int x, int y) {
		this.x = x;
		this.y = y;
		collisionBox.setBounds(x, y, 10, 10);
	}

	@Override
	public void run() {
		while(alive) {
			for(int i=0; i<1000;i++) {
				if(i == 100)
					readyToFire = true;
				x+=direction;
				collisionBox.x+=direction;
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			alive = false;
		}
	}

	public boolean alive = true;
	public boolean readyToFire = false;
	public byte direction = Window.entitys.get(0).facing;
}
