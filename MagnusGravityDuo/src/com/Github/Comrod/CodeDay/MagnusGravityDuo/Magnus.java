package com.Github.Comrod.CodeDay.MagnusGravityDuo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Magnus
{
	static BufferedImage magnusRight;
	
	static int xPos;
	static int dxPos;
	static int yPos;
	static int dyPos;
	
	Magnus()
	{
		try {
    		magnusRight = ImageIO.read(new URL("http://i.imgur.com/6hxdzAt.png"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
}
