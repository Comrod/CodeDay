package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Magnus
{
	private int dx;
	private int dy;
    private int x;
    private int y;
    
    static int moveDirection = 0;
    
    public static BufferedImage magnusStillRight;
    public static BufferedImage magnusStillLeft;
    public static BufferedImage magnusRight;
    public static BufferedImage magnusLeft;
    public static BufferedImage frameRight;
    public static BufferedImage frameLeft;
    
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
    	y = 60;
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
    
    public BufferedImage getImage()
    {
    	return magnusStillRight;
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            moveDirection = 2;
        	dx = -1;
            System.out.println("Left button pressed");
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            moveDirection = 1;
        	dx = 1;
            System.out.println("Right button pressed");
        }

        if (key == KeyEvent.VK_UP)
        {
            dy = -1;
            System.out.println("Up button pressed");
        }

        if (key == KeyEvent.VK_DOWN)
        {
            dy = 1;
            System.out.println("Down button pressed");
        }
    }

    public void keyReleased(KeyEvent e)
    {
    	System.out.println("Key event initialized");
        int key = e.getKeyCode();
        moveDirection = 0;
        
        if (key == KeyEvent.VK_LEFT)
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP)
        {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN)
        {
            dy = 0;
        }
    }
}
