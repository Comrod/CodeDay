package platformer.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import platformer.Object.Bullets;
import platformer.Sound.Sound;
import platformer.Window.Window;

public class Player extends Entity {

	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void run() {
		while(Window.started) {
			if(!dead) {
				handleSideCollision();
				handleMove();
				handleGravity();
				handleStomp();
				if(outofbounds)outofbounds();
				handleDamaged();
				handleDeath();
				coinCollect();
				handleShot();
				if(won) handleWin();
				if(shooting) {
					int bulletPlacing;
					if(facing == 1) bulletPlacing = 65;
					else bulletPlacing = 30;
					if(bullets.size() > 1 && bullets.get(bullets.size()-1).readyToFire)
						handleShoot(new Bullets(getX()+(facing*bulletPlacing),getY()+25));
					else if(bullets.size() <= 1)
						handleShoot(new Bullets(getX()+(facing*bulletPlacing),getY()+25));
				}
			} else {
				handleGravity();
			}
			collisionBox.setBounds(getX(), getY(), getWidth(), getHeight());
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void handleWin() {
		setWidth(200);
		setHeight(200);
	}
	
	public void handleDamaged() {
		for(int i = 1; i < Window.entitys.size(); i++)
			if(collisionBox.intersects(Window.entitys.get(i).collisionBox))
				if(collisionBox.y+collisionBox.height > 5+Window.entitys.get(i).getY() && !Window.entitys.get(i).dead) {
					dead = true;
				}
	}

	public void handleDeath() {
		if(dead) {
			if(Window.music != null) Window.music.stop();
			sound.playSound(0, "./src/platformer/Sound/dead.mid");
			setY(getY()+20);
			setHeight(getHeight()-20);
			//dead = false;
		} 
	}

	public void outofbounds(){
			dead = true;
	}
	public void handleEvent(int eventType) {
		switch(eventType) {
		case 0:
			won = true;
			//outofbounds = true;
			break;
		case 1:
			outofbounds = true;
			break;
		}
	}
	
	public void handleStomp() {
		for(int i = 0; i < Window.entitys.size(); i++)
			if(collisionBox.intersects(Window.entitys.get(i).collisionBox))
				if(collisionBox.y+collisionBox.height <= 1+Window.entitys.get(i).getY()) {
					jump(75, (byte) 2);
					Window.entitys.get(i).dead = true;
					doubleJumped = false;
					Window.gui.points+=200;
					sound.playSound(0, "./src/platformer/Sound/stomp.mid");
				}
	}
	public void handleShot(){
		for(int i=0;i<bullets.size();i++){
			for(int a=1;a<Window.entitys.size();a++)
			if(bullets != null && bullets.get(i).collisionBox.intersects(Window.entitys.get(a).collisionBox)){
				//System.out.println("HIT!");
				Window.entitys.get(a).dead = true;
				bullets.get(i).alive = false;
				
				
			}
		}
	}

	public void coinCollect() {
		for(int i = 0; i < Window.coins.size(); i++)
			if(collisionBox.intersects(Window.coins.get(i).collisionBox)) {
				Window.coins.remove(i);
				Window.gui.coins+=1;
				try {
					sound.playSound(0, "./src/platformer/Sound/coin.mid");
				} catch(NullPointerException e) {

				}
			}
	}

	public void handleGravity() {
		for(int i = 0; i < Window.platforms.size(); i++) {
			if(collisionBox.intersects(Window.platforms.get(i).collisionBox)) {
				if(collisionBox.y+49 <= Window.platforms.get(i).collisionBox.y) {
					colliding = true;
					doubleJumped = false;
					jumping = false;
					if(Window.platforms.get(i).eventPlatform)
						handleEvent(Window.platforms.get(i).action);
					if(Window.platforms.get(i).outofbounds)
						handleEvent(Window.platforms.get(i).action);
				if(collisionBox.intersects(Window.platforms.get(i).collisionBox)){
					if(collisionBox.x + collisionBox.width <= Window.platforms.get(i).collisionBox.x){
						right=false;
					}
				}
					return;
				}
			} else if(!collisionBox.intersects(Window.platforms.get(i).collisionBox)) {
				colliding = false;
			}
		}
		if(!colliding && !jumping) {
			setY(getY()+1);	
			Window.screenY-=1;
		}
	}

	public void handleMove() {
		//if(up) {
			//Window.screenY+=2;
			//setY(getY()-2);
		if(down) {
			setY(getY()+1);
			Window.screenY-=1;
			down = false;
		} if(left) {
			facing = -1;
			Window.screenX++;
			setX(getX()-1);
		} if(right) {
			facing = 1;
			Window.screenX--;
			setX(getX()+1);
		}
	}

	public void handleShoot(Bullets b) {
		bullets.add(b);
		new Thread(b).start();

		for(int i = 0; i < bullets.size(); i++)
			if(!bullets.get(i).alive)
				bullets.remove(i);
		if(bullets.size()>30){
			bullets.get(0).alive = false;
			bullets.remove(0);
		}
	}

	@Override
	public void render(Graphics2D g) {
		for(int i =0; i < bullets.size(); i++)
			bullets.get(i).Render(g);
		g.setColor(Color.lightGray);
		g.fillRect(getX()+(facing*25), getY()+24, 50, 15);
		g.setColor(Color.red);
		g.drawRect(getX()+(facing*25), getY()+24, 50, 15);

		g.setColor(Color.orange);
		g.fillRect(getX(), getY()-1, getWidth(), getHeight());

		/** drawing collision box for debugging */
		if(Window.gui.collisionLines) {
			g.setColor(Color.white);
			g.drawRect(collisionBox.x, collisionBox.y-1, collisionBox.width, collisionBox.height);
		}
	}

	public void jump(final int height, final int jumpSpeed) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				canDoubleJump = false;
				for(int i = 0; i < height; i++) {
					Window.screenY+=jumpSpeed;
					setY(getY()-jumpSpeed);
					collisionBox.y-=jumpSpeed;
					try {
						Thread.sleep(5);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} 
				canDoubleJump = true;
			}
		});t.start();
	}

	public void addBullet(Bullets b) {
		bullets.add(b);
		new Thread(b).start();
	}
	public void handleSideCollision(){
		for(int i = 0; i < Window.platforms.size(); i++) 
		if(collisionBox.intersects(Window.platforms.get(i).collisionBox)){
			if(collisionBox.x + collisionBox.width <= 1+Window.platforms.get(i).collisionBox.x){
				right=false;
				sideCollide=true;
				return;
			}
	} else {
		sideCollide = false;
	}
}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			if(colliding)
				jump(80, (byte) 3);
			else if (canDoubleJump && !colliding && !jumping && !doubleJumped) {
				jump(45, (byte) 4);
				doubleJumped = true;
			}
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_G:
			shooting = true;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_G:
			shooting = false;
			break;
		}
	}

	private boolean up, down, left, right, jumping, shooting,sideCollide;
	public boolean won,outofbounds;
	public ArrayList<Bullets> bullets = new ArrayList<Bullets>();
	public Sound sound = new Sound();
	public boolean canDoubleJump, doubleJumped;
}
