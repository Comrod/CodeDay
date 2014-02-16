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
	
	Random rand = new Random();
	
	//Sets dimensions of platform
	int WIDTH = 338;
	int HEIGHT = 40;
	
	//Sets position of platform
	int xPos;
	int yPos;
	
	static int platformSpeed = -5;
	
	Platform()
	{	
		try {
    		platform = ImageIO.read(new URL("http://i.imgur.com/ZfV0mGS.png"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		
	}
	
	//Draws platform
	public void paint(Graphics g)
	{
		g.drawImage(platform, xPos, yPos, null);
	}
	
	public void platformMovement()
	{
		xPos += platformSpeed;
		Rectangle platformBounds = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
		//Make intersect if statement
	}
	
}
