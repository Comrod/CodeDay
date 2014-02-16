package com.Github.Comrod.CodeDay.MagnusGravityDuo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Game
{
	static BufferedImage background;
	
	Game()
	{
		try {
			background = ImageIO.read(new URL("http://i.imgur.com/9hKYPfK.png"));
			System.out.println("Gotten Background");
		} catch (IOException e) {
			System.out.println("Incorrect Background");
		}
	}
}
