package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel
{
	//Frame Width and Height
	static int WIDTH = 600;
	static int HEIGHT = 400;
	
	Magnus magnus = new Magnus();
	
	//Get Background
	BufferedImage background = null;{
		try {
			background = ImageIO.read(new URL("http://i.imgur.com/YXlnj1g.png"));
			System.out.println("Gotten Background");
		} catch (IOException e) {
			System.out.println("WRONG BACKGROUND");
		}}
	
	public Game()
	{
		
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		System.out.println("Graphics Painted");
		g.drawImage(background, 0, 0, null);
		
	}
	
}
