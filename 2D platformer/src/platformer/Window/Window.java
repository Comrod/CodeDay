package platformer.Window;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.ImageCapabilities;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import platformer.Entity.*;
import platformer.Object.Bullets;
import platformer.Object.Coin;
import platformer.Object.Platform;
import platformer.Object.PowerUp;
import platformer.Sound.Music;

public class Window extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	public Window(int X, int Y) {
		this.X = X;
		this.Y = Y;
		setTitle("Platformer");
		setSize(X, Y);
		setBackground(Color.lightGray);
		setForeground(Color.black);
		setResizable(true);
		setFocusable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		texts.add(0, new Text(new StringBuffer("(R)eset"), 25, 50, true));
		texts.add(1, new Text(new StringBuffer("(O)ptions"), 150, 50, true));
		texts.add(2, new Text(new StringBuffer("Exit"), 306, 50, true));
		texts.add(3, new Text(new StringBuffer("Start"), 150, 200, true));
		music.playMusic(1000, "./src/platformer/Sound/theme.mid");
	}

	@Override
	public void run() {
		handleLevels();
		while(mainStarted) {
			try {
				buffer(this.getGraphics());
				frameCount++;
				if(gui.frameLimiter) {
					Thread.sleep(10);
				} if((System.currentTimeMillis()/100) > bufferTime+10) {
					bufferTime = System.currentTimeMillis()/100;
					BPS = frameCount;
					frameCount = 0;
					freemem = Runtime.getRuntime().freeMemory()/1000000;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public void handleLevels() {
		switch(currentLevel) {
		case -1:

			break;
		case 0:
			started = true;
			addEntity(0, new Player((getWidth()/2)-25, (getHeight()/2)-25, 50, 50));
			addPowerUp(new PowerUp(200, 200));
			addPowerUp(new PowerUp(100, 100));
			addPowerUp(new PowerUp(150, 150));
			addEntity(new DumbEnemy(50, 150, 40, 40));
			addEntity(new DumbEnemy(520, 150, 40, 40));
			addEntity(new DumbEnemy(780, 150, 40, 40));
			addEntity(new DumbEnemy(2650, 150, 70, 70));
			addPlatform(new Platform(75, 60, 200, 40));
			addPlatform(new Platform(50, 500, 200, 50));
			addPlatform(new Platform(0, 400, 400, 50));
			addPlatform(new Platform(500, 300, 200, 50));
			addPlatform(new Platform(1400, 300, 475, 100));
			addPlatform(new Platform(750, 230, 500, 50));
			addPlatform(new Platform(1900, 350, 150, 50, "moving", 2600));
			coins.add(new Coin(75, 30));
			coins.add(new Coin(105, 30));
			coins.add(new Coin(135, 30));
			coins.add(new Coin(165, 30));
			coins.add(new Coin(195, 30));
			coins.add(new Coin(225, 30));
			coins.add(new Coin(255, 30));
			coins.add(new Coin(2000, 310));
			coins.add(new Coin(2030, 310));
			coins.add(new Coin(2060, 310));
			coins.add(new Coin(2090, 310));
			coins.add(new Coin(2120, 310));
			coins.add(new Coin(2150, 310));
			coins.add(new Coin(2180, 310));
			coins.add(new Coin(2210, 310));
			coins.add(new Coin(2240, 310));
			coins.add(new Coin(2270, 310));
			coins.add(new Coin(2300, 310));
			coins.add(new Coin(2330, 310));
			coins.add(new Coin(2360, 310));
			coins.add(new Coin(2390, 310));
			coins.add(new Coin(2420, 310));

			addPlatform(new Platform(2600, 300, 500, 50));
			
			coins.add(new Coin(2600, 270));
			coins.add(new Coin(2630, 270));
			coins.add(new Coin(2660, 270));
			coins.add(new Coin(2690, 270));
			coins.add(new Coin(2720, 270));
			coins.add(new Coin(2750, 270));
			coins.add(new Coin(2780, 270));
			coins.add(new Coin(2810, 270));
			coins.add(new Coin(2840, 270));
			coins.add(new Coin(2870, 270));
			coins.add(new Coin(2900, 270));
			coins.add(new Coin(2930, 270));
			coins.add(new Coin(2960, 270));
			coins.add(new Coin(2990, 270));
			coins.add(new Coin(3020, 270));
			coins.add(new Coin(3050, 270));
			
	
			addPlatform(new Platform(3300, 200, 50, 50));
			coins.add(new Coin(3310, 170));
			addEntity(new DumbEnemy(3300, 150, 40, 40));

			addPlatform(new Platform(3450, 300, 200, 50));
			addEntity(new DumbEnemy(3450, 150, 40, 40));
			addEntity(new DumbEnemy(3450, 150, 40, 40));

			addPlatform(new Platform(3800, 100, 50, 50, true, 0));
			
			addPlatform(new Platform(0,900,999999999,1,true,1, false));
			
			coins.add(new Coin(1400, 270));
			coins.add(new Coin(1430, 270));
			coins.add(new Coin(1460, 270));
			coins.add(new Coin(1490, 270));
			coins.add(new Coin(1520, 270));
			coins.add(new Coin(1550, 270));
			coins.add(new Coin(1580, 270));
			coins.add(new Coin(1610, 270));
			coins.add(new Coin(1640, 270));
			coins.add(new Coin(1670, 270));
			coins.add(new Coin(1700, 270));
			coins.add(new Coin(1730, 270));
			coins.add(new Coin(1760, 270));
			coins.add(new Coin(1790, 270));
			coins.add(new Coin(1820, 270));
			coins.add(new Coin(1850, 270));

			coins.add(new Coin(500, 270));
			coins.add(new Coin(530, 270));
			coins.add(new Coin(560, 270));
			coins.add(new Coin(590, 270));
			coins.add(new Coin(620, 270));
			coins.add(new Coin(650, 270));
			coins.add(new Coin(680, 270));
			break;
		case 1:

			break;

		}
	}

	public void clear(boolean restart) {
		try {
			if(restart)
				mainStarted = false;
			started = false;
			screenX = screenY = 0;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			powerups = null;
			coins = null;
			platforms = null;
			entitys = null;
			gui = null;
			if(restart) {
				mainStarted = true;
				new Thread(this).start();
			}
			powerups = new ArrayList<PowerUp>();
			coins = new ArrayList<Coin>();
			entitys = new ArrayList<Entity>();
			platforms = new ArrayList<Platform>();
			gui = new GUI(screenX, screenY);
			music.playMusic(1000, "./src/platformer/Sound/theme.mid");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void empty() {
		try {			screenX = screenY = 0;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			//powerups = null;
			//coins = null;
			//platforms = null;
			//entitys = null;
			//gui = null;
			powerups = new ArrayList<PowerUp>();
			coins = new ArrayList<Coin>();
			entitys = new ArrayList<Entity>();
			platforms = new ArrayList<Platform>();
			gui = new GUI(screenX, screenY);
			music.playMusic(1000, "./src/platformer/Sound/theme.mid");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Quick method to add Entitys quicker(create entity, Add to arraylist, start Thread) */
	public void addEntity(Entity e) {
		entitys.add(e);
		new Thread(e).start();
	}

	public void addEntity(int slot, Entity e) {
		entitys.add(slot, e);
		new Thread(e).start();
	}

	/** quickly add platforms and all their functions to the window */
	public void addPlatform(Platform p) {
		platforms.add(platforms.size(), p);
		new Thread(p).start();
	}

	public void addPowerUp(PowerUp p) {
		powerups.add(p);
		new Thread(p).start();
	}

	/** Double buffer the graphics 
	 * @throws AWTException **/
	public void buffer(Graphics g) throws AWTException {
		if(img == null)
			img = createVolatileImage(getWidth(), getHeight(), new ImageCapabilities(false));
		render(img.createGraphics());  
		g.setClip(3, 24, getWidth()-6, getHeight()-27);
		g.drawImage(img, 0, 24, this);
	}

	/** Draws the game */
	public void render(Graphics2D g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(gameState == 0)
			drawMenu(g);
		else if(gameState == 1)
			drawGame(g);

	}

	public void drawMenu(Graphics2D g) {
		g.setColor(Color.blue);
		for(int i = 0; i < getHeight(); i+=3)
			g.fillRect(0, i, getWidth(), 1);
		for(int i = 0; i < getWidth(); i+=3)
			g.fillRect(i, 0, 1, getHeight());
		texts.get(3).render(g);
		texts.get(1).render(g);
		if(gui.options)
			gui.optionsInterface(g);
	}

	public void drawGame(Graphics2D g) {
		if(gui!= null && gui.backGround) {
			if(!gui.solidBackground)
				if(powerups != null && powerups.size() > 0)
					try {
						g.setColor(new Color(0, powerups.get(1).red, powerups.get(1).red));
					} catch(Exception e) {}
				else
					g.setColor(Color.blue);
			for(int i = 0; i < getHeight(); i+=3)
				g.fillRect(0, i, getWidth(), 1);
			for(int i = 0; i < getWidth(); i+=3)
				g.fillRect(i, 0, 1, getHeight());
		}
		if(gui.antialiasing) {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		bufferCount++;

		if((System.currentTimeMillis()/100) >= renderTime+10) {
			renderTime = System.currentTimeMillis()/100;
			RPS = bufferCount;
			bufferCount = 0;
		}
		g.setColor(Color.yellow);
		g.drawString("Buffers per second: "+BPS, 475, 20);
		g.setColor(Color.yellow);
		g.drawString("Renders Per second: "+RPS, 475, 31);
		g.drawString(freemem+"/"+Runtime.getRuntime().totalMemory()/1000000+" : "+Runtime.getRuntime().maxMemory()/1000000, 475, 42);
		if(gui.quickInterface) {
			gui.render(g);
			for(int i = 0; i < texts.size(); i++)
				if(i != 3)
					texts.get(i).render(g);
		}
		g.translate(screenX, screenY);
		for(int i = 0; i < entitys.size(); i++)
			entitys.get(i).render(g);
		for(int i = 0; i < platforms.size(); i++)
			platforms.get(i).render(g);
		for(int i = 0; i < coins.size(); i++)
			coins.get(i).render(g);
		for(int i = 0; i < powerups.size(); i++)
			powerups.get(i).render(g);
		if(!gui.quickInterface) {
			g.translate(screenX-(screenX*2), screenY-(screenY*2)); //looks bad
			gui.render(g);
			for(int i = 0; i < texts.size(); i++)
				texts.get(i).render(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		entitys.get(0).keyPressed(e);
		switch(e.getKeyCode()) {
		case KeyEvent.VK_MINUS:
			music.stop();
			break;
		case KeyEvent.VK_EQUALS:
			music.playMusic(1000, "./src/platformer/Sound/theme.mid");
			break;
		case KeyEvent.VK_R:
			clear(true);
			break;
		case KeyEvent.VK_O:
			if(gui.options)
				gui.options = false;
			else
				gui.options = true;
			break;
		case KeyEvent.VK_ESCAPE:
			clear(false);
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_M:

			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		entitys.get(0).keyReleased(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(texts.get(3).isMousedOver && gameState == 0) {
			currentLevel = 0;
			gameState = 1;
			handleLevels();
		}
		if(texts.get(0).isMousedOver) {
			clear(true);
		}
		if(texts.get(1).isMousedOver) {
			if(gui.options)
				gui.options = false;
			else
				gui.options = true;
		}
		if(texts.get(2).isMousedOver) {
			clear(false);
			gameState = 0;
			currentLevel = -1;
		}
		try {
			if(gui.boxs.get(0).isMousedOver && gui.options) {
				if(gui.collisionLines)
					gui.collisionLines = false;
				else
					gui.collisionLines = true;
			}
			if(gui.boxs.get(1).isMousedOver && gui.options) {
				if(gui.frameLimiter)
					gui.frameLimiter = false;
				else
					gui.frameLimiter = true;
			}
			if(gui.boxs.get(2).isMousedOver && gui.options) {
				if(gui.antialiasing)
					gui.antialiasing = false;
				else
					gui.antialiasing = true;
			}
			if(gui.boxs.get(3).isMousedOver && gui.options) {
				if(gui.backGround)
					gui.backGround = false;
				else
					gui.backGround = true;
			}
			if(gui.boxs.get(4).isMousedOver && gui.options) {
				if(gui.quickInterface)
					gui.quickInterface = false;
				else
					gui.quickInterface = true;
			}
			if(gui.boxs.get(5).isMousedOver && gui.options) {
				if(gui.solidBackground)
					gui.solidBackground = false;
				else
					gui.solidBackground = true;
			}
		} catch(Exception e1) {}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for(int i = 0; i < texts.size(); i++)
			texts.get(i).mouseMoved(e);
		for(int i = 0; i < gui.boxs.size(); i++)
			gui.boxs.get(i).mouseMoved(e);
	}

	public static int screenX = 0, screenY = 0;
	public static int X, Y;
	public boolean up, down, left, right;
	VolatileImage img;
	public int frameCount = 0, bufferCount = 0;
	public int BPS;
	public int RPS;
	public long freemem = Runtime.getRuntime().freeMemory()/1000000;
	public long renderTime = System.currentTimeMillis()/100;
	public long bufferTime = System.currentTimeMillis()/100;
	public byte currentLevel = -1;
	public byte gameState = 0;
	public static boolean started, mainStarted = true;
	public static ArrayList<Text> texts = new ArrayList<Text>();
	public static ArrayList<Text> optionText = new ArrayList<Text>();
	public static Music music = new Music();
	public static GUI gui = new GUI(screenX, screenY);
	public static ArrayList<Coin> coins = new ArrayList<Coin>();
	public static ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
	public static ArrayList<Entity> entitys = new ArrayList<Entity>();
	public static ArrayList<Platform> platforms = new ArrayList<Platform>();
}
