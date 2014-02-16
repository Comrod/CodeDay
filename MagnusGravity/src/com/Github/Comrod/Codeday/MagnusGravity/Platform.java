package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

public class Platform
{
	public static BufferedImage platform;
	
	static Random rand = new Random();
	
	//Sets dimensions of platform
	int WIDTH = 338;
	int HEIGHT = 40;
	
	//Sets position of platform
	private int xPos = rand.nextInt(1000) + 200;
	private int yPos = rand.nextInt(800) + 600;
	
	static int platformSpeed = -7;
	
	
	Platform()
	{	
		try {
    		platform = ImageIO.read(new URL("http://i.imgur.com/ZfV0mGS.png"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		
		platformMovement();
		System.out.println("Platform() run");
	}
	
	void onSolidGround()
	{
		Rectangle platformBounds = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
		if (platformBounds.intersects(Magnus.getBounds()))
		{
			Magnus.dy = 0;
			System.out.println("On solid ground");
		}
		else
		{
			Magnus.dy += 0.01;
			System.out.println("Not on solid ground");
		}
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(Platform.platform, xPos, yPos, null);
	}
	
	
	public void platformMovement()
	{
		xPos += platformSpeed;
		if (xPos <= 0 - WIDTH)
		{
			xPos = rand.nextInt(1000) + 1;
			yPos = rand.nextInt(800) + 600;
		}
		System.out.println("Platforms are moving");
	}
	
}
