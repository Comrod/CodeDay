package com.Github.Comrod.CodeDay.GravityGame;

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

	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 4;
	
	public static final String NAME = "Gravity Game";
	
	private JFrame frame;
	
	public boolean running = false;
	
	public int tickCount = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Main()
	{
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		frame = new JFrame(NAME);
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
		
		//g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		g.drawImage(Magnus.image, (WIDTH*SCALE/2), (HEIGHT*SCALE/2), null);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args)
	{
		new Main().start();
	}
	
}
