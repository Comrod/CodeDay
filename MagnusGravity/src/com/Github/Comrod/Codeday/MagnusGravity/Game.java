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
	
	//Frame Width and Height
	static int WIDTH = 600;
	static int HEIGHT = 400;
	
	private boolean movingRight = true;
	private boolean movingLeft = true;
	
	Timer timer;
	Magnus magnus;
	
	//Get Background
	BufferedImage background = null;{
		try {
			background = ImageIO.read(new URL("http://i.imgur.com/YXlnj1g.png"));
			System.out.println("Gotten Background");
		} catch (IOException e) {
			System.out.println("Incorrect Background");
		}}
	
	public Game()
	{
		addKeyListener(new TAdapter());
		
		setFocusable(true);
        setDoubleBuffered(true);
        requestFocusInWindow();
        
        magnus = new Magnus();
        
        //Game Timer
        timer = new Timer(5, this);
        timer.start();
        
        //Animation Timer Right
        Timer animationTimerRight = new Timer(250, new ActionListener(){	
            public void actionPerformed(ActionEvent e) {
            	movingRight = !movingRight;
                Magnus.frameRight = movingRight ? Magnus.magnusStill : Magnus.magnusRight;
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
                Magnus.frameLeft = movingLeft ? Magnus.magnusStill : Magnus.magnusLeft;
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
			g.drawImage(Magnus.magnusStill, magnus.getX(), magnus.getY(), this);
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
	}
	
	public void actionPerformed(ActionEvent e)
	{
		magnus.move();
		repaint();
	}
	
	private class TAdapter extends KeyAdapter
	{
		public void keyReleased(KeyEvent e)
        {
            magnus.keyReleased(e);
        }
		
		public void keyPressed(KeyEvent e)
        {
            magnus.keyPressed(e);
            System.out.println("Key Pressed");
        }
		
    }
}
