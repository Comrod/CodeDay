package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

public class Platform
{
	public static BufferedImage platform;
	
	Random rand = new Random();
	
	//Sets dimensions of platform
	int WIDTH = 338;
	int HEIGHT = 40;
	
	//Sets position of platform
	static int xPos;
	static int yPos = 530;
	
	static int platformSpeed = -5;
	
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
	
	public void platformMovement()
	{
		xPos += platformSpeed;	
		if (xPos <= 0 - WIDTH)
		{
			xPos = Main.WIDTH;
		}
		System.out.println("Platforms are moving");
	}
	
}
