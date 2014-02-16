package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Magnus
{
	//Vars for placement of Magnus
	public static int dx;
	public static float dy;
    public static int x;
    public static int y;
    
    //Determines direction for animation
    static int moveDirection = 0;
    
    //Images for movement animations
    public static BufferedImage magnusStillRight;
    public static BufferedImage magnusStillLeft;
    public static BufferedImage magnusRight;
    public static BufferedImage magnusLeft;
    public static BufferedImage frameRight;
    public static BufferedImage frameLeft;
    
    static int WIDTH = 30;
    static int HEIGHT = 130;
    
    //Limits Magnus to a double jump
    public int jumpLimit = 2;
    public int jumpLimitReset;
    
    //Gravity Vars
    static int acceleration = 1;
    static int gravitySpeed = 1;
    
    public Magnus()
    {
    	//Set Textures
    	try {
    		magnusStillRight = ImageIO.read(new URL("http://i.imgur.com/u8cXxLM.png"));
    		magnusStillLeft = ImageIO.read(new URL("http://i.imgur.com/fFFh0vo.png"));
    		magnusRight = ImageIO.read(new URL("http://i.imgur.com/6hxdzAt.png"));
    		magnusLeft = ImageIO.read(new URL("http://i.imgur.com/TcjDPzV.png"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	System.out.println("Gotten Magnus");

    	//Set Position
    	x = 40;
    	y = 200;
    }
    
    public void gravity()
    {
    	/*while (Main.ticks < 30)
    	{
    		dy = 1;
    	}*/
    }
    
    public void move()
    {
    	x += dx;
    	y += dy;
    }
	
    public int getX()
    {
    	return x;
    }
    
    public int getY()
    {
    	return y;
    }
    
    public static Rectangle getBounds()
    {
    	return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
}
