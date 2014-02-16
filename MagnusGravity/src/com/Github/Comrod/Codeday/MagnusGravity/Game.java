package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.Font;
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
import java.util.Random;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel implements ActionListener
{
	Random rand = new Random();
	
	private static final long serialVersionUID = 1L;
	
	//For Walking Animation
	private boolean movingRight = true;
	private boolean movingLeft = true;
	
	//Score
	public static int score = 0;
	
	Timer timer;
	Magnus magnus;
	Platform platform;
	
	//Get Background
	BufferedImage background = null;{
		try {
			background = ImageIO.read(new URL("http://i.imgur.com/9hKYPfK.png"));
			System.out.println("Gotten Background");
		} catch (IOException e) {
			System.out.println("Incorrect Background");
		}}
	
	public Game()
	{
		addKeyListener(new KeyInput(this));
		
		setFocusable(true);
        setDoubleBuffered(true);
        requestFocusInWindow();
        
        magnus = new Magnus();
        platform = new Platform();
        
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
	
	public void paint(Graphics g)
	{
		super.paint(g);
		//System.out.println("Graphics Painted");
		g.drawImage(background, 0, 0, null);
		
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
		
		g.setFont(new Font("Helvetica", Font.BOLD, 30));
		g.drawString("Score: " + score, 550, 150);
		
	}
	
	void increaseDifficulty()
	{;
		double foo = -0.05;
		Platform.platformSpeed = (float) (foo + Platform.platformSpeed);
		Magnus.dy = (float) ((-foo) + Magnus.dy);
		System.out.println("Difficulty Level: " + (-foo));
	}
	
	public void actionPerformed(ActionEvent e)
	{
		magnus.move();
		repaint();
	}
	
	//KEY INPUT --- DO NOT TOUCH OR IT WILL BREAK
	 public void keyPressed(KeyEvent e)
	    {
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
	            /*if (jumpLimit <= 2)
	        	{
	            	dy = -15;
	        	}*/
	        	Platform.jumpLimitReset++;
	        	if (Platform.jumpLimitReset < 3)
	        	{
	        		Magnus.dy = -5;
	        	}
	            System.out.println("Up key or Space bar pressed");
	        }
	    }

	    public void keyReleased(KeyEvent e)
	    {
	    	System.out.println("Key event initialized");
	        int key = e.getKeyCode();
	        //moveDirection = 0;
	        
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
	        	Magnus.dy = (float) 1;	
	        }
	    }
}
