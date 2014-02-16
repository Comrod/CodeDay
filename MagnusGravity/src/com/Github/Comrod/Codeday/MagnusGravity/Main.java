package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends Canvas implements Runnable
{
	
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	
	public static final String NAME = "Magnus Gravity";
	
	private Frame frame;
	
	public boolean running = false;
	
	public int tickCount = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	Magnus magnus = new Magnus();
	
	public Main()
	{
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		frame = new Frame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public synchronized void start()
	{
		running = true;
		new Thread(this).start();	
	}
	
	public synchronized void stop()
	{
		running = false;
	}
	
	public void run()
	{
		final Game game = new Game();
		
		//Frame
		frame.add(game);
		game.setVisible(true);
		frame.revalidate();
		frame.repaint();
		System.out.println("Game started");
		
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while(delta >= 1)
			{
				ticks ++;
				tick();
				delta -= 1;
				shouldRender = true;

				//GRAVITY GRAVITY GRAVITY ----- VERY IMPORTANT STUFF HERE, DO NOT MISS
				if (Magnus.y < Platform.yPos - 130)
				{
					Magnus.dy += 1;
				}
				else
				{
					Magnus.dy = 0;
					Magnus.y = Platform.yPos - 130;
				}
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			if (shouldRender)
			{
				frames ++;
				render();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000)
			{
				lastTimer += 1000;
				System.out.println("Ticks: " + ticks + ", Frames:" + frames);
				frames = 0;
				ticks = 0;
			}	
		}
	}
	
	public void tick()
	{
		tickCount ++;
		
		for (int i = 0; i < pixels.length; i++)
		{
			pixels[i] = i + tickCount;
		}
	}
	
	//Renders graphics and textures
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		bs.show();
		
		g.drawImage(Game.background, 0, 0, null);
		
		if (Magnus.moveDirection == 0)
		{
			//Not moving
			g.drawImage(Magnus.magnusStillRight, magnus.getX(), magnus.getY(), this);
		}
		else if (Magnus.moveDirection == 1)
		{
			//Moving Right
			g.drawImage(Magnus.frameRight, magnus.getX(), magnus.getY(), this);
		}
		else if (Magnus.moveDirection == 2)
		{
			//Moving Left
			g.drawImage(Magnus.frameLeft, magnus.getX(), magnus.getY(), this);
		}
		
		g.drawImage(Platform.platform, Platform.xPos, Platform.yPos, null);
		
		//Disposes old textures for new ones
		g.dispose();
	}
	
	public static void main(String[] args)
	{
		new Main().start();
	}
	
}
