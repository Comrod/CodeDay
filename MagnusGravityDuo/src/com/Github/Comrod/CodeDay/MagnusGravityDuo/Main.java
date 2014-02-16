package com.Github.Comrod.CodeDay.MagnusGravityDuo;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main implements Runnable
{
   
   final int WIDTH = 1200;
   final int HEIGHT = 800;
   
   Magnus magnus;
   
   static BufferedImage background;
   
   JFrame frame;
   Canvas canvas;
   BufferStrategy bufferStrategy;
   
   public Main(){
      frame = new JFrame("Magnus Gravity");
      
      JPanel panel = (JPanel) frame.getContentPane();
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      panel.setLayout(null);
      
      
      canvas = new Canvas();
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
      
      panel.add(canvas);
      
      canvas.addKeyListener(new KeyInput(this));
      canvas.addMouseListener(new MouseControl());
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setResizable(false);
      frame.setVisible(true);
      
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      
      canvas.requestFocus();
      
      //Get Textures
      try {
			background = ImageIO.read(new URL("http://i.imgur.com/9hKYPfK.png"));
			System.out.println("Recieved Textures");
		} catch (IOException e) {
			System.out.println("Incorrect Textures");
		}
   }
        
   private class MouseControl extends MouseAdapter
   {
      
   }
   
   long desiredFPS = 60;
    long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
    
   boolean running = true;
   
   public void run()
   {
	  
      magnus = new Magnus();
      
      long beginLoopTime;
      long endLoopTime;
      long currentUpdateTime = System.nanoTime();
      long lastUpdateTime;
      long deltaLoop;
      
      while(running){
         
    	  beginLoopTime = System.nanoTime();
         
         render();
         
         lastUpdateTime = currentUpdateTime;
         currentUpdateTime = System.nanoTime();
         update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
         
         endLoopTime = System.nanoTime();
         deltaLoop = endLoopTime - beginLoopTime;
           
           if(deltaLoop > desiredDeltaLoop){
               //Do nothing. We are already late.
           }else{
               try{
                   Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
               }catch(InterruptedException e){
                   //Do nothing
               }
           }
      }
   }
   
   private void render()
   {
      Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
      g.clearRect(0, 0, WIDTH, HEIGHT);
      render(g);
      g.dispose();
      bufferStrategy.show();
   }
   
   //TESTING
   private double x = 0;
   
   /**
    * Rewrite this method for your game
    */
   protected void update(int deltaTime){
      x += deltaTime * 0.2;
      while(x > 500){
         x -= 500;
      }
   }
   
   /**
    * Rewrite this method for your game
    */
   protected void render(Graphics2D g)
   {
      //g.fillRect((int)x, 0, 200, 200);
	   g.drawImage(background, 0, 0, null);
	   g.drawImage(Magnus.magnusRight, 40, 40, null);
	   
   }
   
   //Get Key Inputs
   public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT)
		{
			Magnus.dxPos =+1;
		}
		else if (key == KeyEvent.VK_LEFT)
		{
			Magnus.dxPos =-1;
		}
		else if (key == KeyEvent.VK_UP)
		{
			Magnus.dyPos =-1;
		}
		else if (key == KeyEvent.VK_DOWN)
		{
			Magnus.dyPos =+  1;
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT)
		{
			Magnus.dxPos = 0;
		}
		else if (key == KeyEvent.VK_LEFT)
		{
			
		}
		else if (key == KeyEvent.VK_UP)
		{
			
		}
		else if (key == KeyEvent.VK_DOWN)
		{
			
		}
	}
   
   public static void main(String [] args){
      Main ex = new Main();
      new Thread(ex).start();
   }

}