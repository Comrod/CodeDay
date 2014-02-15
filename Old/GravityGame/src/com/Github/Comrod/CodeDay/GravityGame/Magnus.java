package com.Github.Comrod.CodeDay.GravityGame;

import java.awt.image.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.*;

public class Magnus
{
	static BufferedImage image = null;{
		try {
			//image = ImageIO.read(getClass().getResource("/Users/cormacchester/Desktop/Coding/CodeDay/GravityGame/res/magnus.png"));
			image = ImageIO.read(new URL("http://i.imgur.com/mw0ai3K.png"));
			System.out.println("Magnus Accessed");
		} catch (IOException e) {
			System.out.println("Error getting image");
		}}
	
	
}
