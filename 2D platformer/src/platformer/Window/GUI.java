package platformer.Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GUI {
	
	public GUI(int offsetX, int offsetY) {
		boxs.add(0, new ClickBox(180, 80, 25, 25));
		boxs.add(1, new ClickBox(180, 120, 25, 25));
		boxs.add(2, new ClickBox(180, 160, 25, 25));
		boxs.add(3, new ClickBox(180, 200, 25, 25));
		boxs.add(4, new ClickBox(180, 240, 25, 25));
		boxs.add(5, new ClickBox(390, 200, 25, 25));
	}
	
	public void render(Graphics2D g) {
		try {
			Font font = new Font("Monospaced", Font.BOLD, 24);
			g.setFont(font);
			
			if(options)
				optionsInterface(g);
			
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("coins: "+coins, 19, 24);
			g.setColor(Color.white);
			g.drawString("coins: "+coins, 20, 25);
			g.setColor(Color.black);
			g.drawString("points: "+points, 254, 24);
			g.setColor(Color.white);
			g.drawString("points: "+points, 255, 25);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void optionsInterface(Graphics2D g) {
		g.setFont(new Font("Monospaced", Font.BOLD, 16));
		g.setColor(Color.gray);
		g.fillRoundRect(175, 55, 315, 200, 10, 10);
		
		g.setColor(Color.yellow);
		if(collisionLines) g.fillRect(180, 60, 25, 25);
		if(!collisionLines) g.drawRect(180, 60, 25, 25);
		
		g.drawString("Draw collision boxes: "+collisionLines, 215, 75);
		
		g.setColor(Color.cyan);
		if(frameLimiter) g.fillRect(180, 100, 25, 25);
		if(!frameLimiter) g.drawRect(180, 100, 25, 25);
		//box 0
		g.drawString("Frame limiter: "+frameLimiter, 215, 115);
		
		g.setColor(Color.green);
		if(antialiasing) g.fillRect(180, 140, 25, 25);
		if(!antialiasing) g.drawRect(180, 140, 25, 25);
		//box 1
		g.drawString("Antialiasing: "+antialiasing, 215, 155);
		
		g.setColor(Color.red);
		if(backGround) g.fillRect(180, 180, 25, 25);
		if(!backGround) g.drawRect(180, 180, 25, 25);
		//box 2
		g.drawString("Background: "+backGround, 215, 195);
		
		g.setColor(Color.orange);
		if(quickInterface) g.fillRect(180, 220, 25, 25);
		if(!quickInterface) g.drawRect(180, 220, 25, 25);
		//box 3
		g.drawString("Fast interface: "+quickInterface, 215, 235);
		
		g.setColor(Color.red);
		if(solidBackground) g.fillRect(390, 180, 25, 25);
		if(!solidBackground) g.drawRect(390, 180, 25, 25);
		//box 4
		g.drawString("Solid", 425, 195);
	}
	
	/** Options states **/
	ArrayList<ClickBox> boxs = new ArrayList<ClickBox>();
	public boolean collisionLines = true;
	public boolean frameLimiter = false;
	public boolean antialiasing = false;
	public boolean backGround = true;
	public boolean quickInterface = true;
	public boolean solidBackground = false;
	public boolean options = false;
	
	public byte coins;
	public int points;
}
