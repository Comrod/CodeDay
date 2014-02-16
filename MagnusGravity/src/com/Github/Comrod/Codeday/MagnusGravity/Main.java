package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

//import com.Github.Comrod.CodeDay.GravityGame.Magnus;


public class Main implements Runnable
{
	
	static JFrame frame = new JFrame();
	static int WIDTH = 1200;
	static int HEIGHT = 800;
	
	public static JLabel scoreLabel = new JLabel();
	
	public static double tickCount = 0;
	public static double ticks = 0;
	public boolean running = false;
	public static boolean gravityOnOff = true;
	
	
	//Rendering
	//private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	//private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Main()
	{
		//Frame
		frame.setSize(WIDTH, HEIGHT);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
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

	@Override
	public void run()
	{
		final Game game = new Game();
		final Platform platform = new Platform();
		
		//Frame
		frame.add(game);
		game.setVisible(true);
		frame.revalidate();
		frame.repaint();
		
		//Score Label
		frame.add(scoreLabel);
		scoreLabel.setVisible(true);
		scoreLabel.setLocation(100, 700);
		scoreLabel.setSize(10, 30);
		scoreLabel.setFocusable(true);
		
		System.out.println("Game started");
		
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
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
				
				//GRAVITY GRAVITY - VERY IMPGRAVITY ----ORTANT STUFF HERE, DO NOT MISS
				platform.onSolidGround();

				//Platform Movement
				platform.platformMovement();
				
				//Increase Difficulty
				game.increaseDifficulty();
				
				scoreLabel.setText("Score: " + Game.score);
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
				//render();
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
		
		/*for (int i = 0; i < pixels.length; i++)
		{
			pixels[i] = i + tickCount;
		}*/
	}
	
	/*public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.dispose();
		bs.show();
	}*/
	
	public static void main(String[] args)
	{
		new Main().start();
	}
}
