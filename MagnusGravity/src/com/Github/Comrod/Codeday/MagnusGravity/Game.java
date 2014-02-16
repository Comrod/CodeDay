package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	public static BufferedImage background;
	
	//For Walking Animation
	private boolean movingRight = true;
	private boolean movingLeft = true;
	
	Timer timer;
	Magnus magnus;
	Platform platform;
	
	//Get Background
	
	
	public Game()
	{
		addKeyListener(new TAdapter());
		
		setFocusable(true);
        setDoubleBuffered(true);
        requestFocusInWindow();
        
        magnus = new Magnus();
        platform = new Platform();
        
        try {
			background = ImageIO.read(new URL("http://i.imgur.com/9hKYPfK.png"));
			System.out.println("Gotten Background");
		} catch (IOException e) {
			System.out.println("Incorrect Background");
		}
        
        //Game Timer
        timer = new Timer(5, this);
        timer.start();
        
        //Animation Timer Right
        Timer animationTimerRight = new Timer(200, new ActionListener(){	
            public void actionPerformed(ActionEvent e) {
            	movingRight = !movingRight;
                Magnus.frameRight = movingRight ? Magnus.magnusRight : Magnus.magnusStillRight;
                repaint();
            }
        });
    	animationTimerRight.setRepeats(true);
    	animationTimerRight.setCoalesce(true);
    	animationTimerRight.start();
    	
    	//Animation Timer Left
        Timer animationTimerLeft = new Timer(250, new ActionListener(){	
            public void actionPerformed(ActionEvent e) {
            	movingLeft = !movingLeft;
                Magnus.frameLeft = movingLeft ? Magnus.magnusLeft : Magnus.magnusStillLeft;
                repaint();
            }
        });
    	animationTimerLeft.setRepeats(true);
    	animationTimerLeft.setCoalesce(true);
    	animationTimerLeft.start();
        
        System.out.println("Game Intialized");
	}
	
	public void actionPerformed(ActionEvent e)
	{
		magnus.move();
		repaint();
	}
	
	public class TAdapter extends KeyAdapter
	{	
		public void keyReleased(KeyEvent e)
        {
            //magnus.keyReleased(e);
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_LEFT)
	        {
				Magnus.dx = 0;
	        }

	        if (key == KeyEvent.VK_RIGHT)
	        {
	        	Magnus.dx = 0;
	        }

	        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_SPACE)
	        {
	        	Magnus. dy = 0;
	        }

	        if (key == KeyEvent.VK_DOWN)
	        {
	        	Magnus.dy = 0;
	        }               
        }
		public void keyPressed(KeyEvent e)
        {
			magnus.keyPressed(e);
            System.out.println("Key Pressed");
            
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT)
            {
                Magnus.moveDirection = 2;
            	Magnus.dx = -1;
                System.out.println("Left key pressed");
            }

            if (key == KeyEvent.VK_RIGHT)
            {
            	Magnus.moveDirection = 1;
            	Magnus.dx = 1;
                System.out.println("Right key pressed");
            }

            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_SPACE)
            {
                if (Magnus.jumpLimit <= 2)
            	{
                	Magnus.dy = -15;
            	}
                
                System.out.println("Up key or Space bar pressed");
            }

            if (key == KeyEvent.VK_DOWN)
            {
            	Magnus.dy = 1;
                System.out.println("Down key pressed");
            }
        }
		
    }
}
