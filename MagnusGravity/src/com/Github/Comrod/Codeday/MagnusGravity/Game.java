package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.Action;
import java.io.IOException;
import java.net.URL;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private boolean movingBool = false;
	private BufferedImage frame;
	
	//Frame Width and Height
	static int WIDTH = 600;
	static int HEIGHT = 400;
	
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
        
        //Animation Timer
        /*Timer animationTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movingBool = !movingBool;
                frame = movingBool ? Magnus.magnusStill : Magnus.magnusRight;
                repaint();
            }
        });
    	animationTimer.setRepeats(true);
    	animationTimer.setCoalesce(true);
    	animationTimer.start();*/
        
        System.out.println("Game Intialized");
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		//System.out.println("Graphics Painted");
		g.drawImage(background, 0, 0, null);
		g.drawImage(Magnus.magnusStill, magnus.getX(), magnus.getY(), this);
		
		/*if (Magnus.determineDirection == 2)
		{
			//Walking right
			g.drawImage(frame, magnus.getX(), magnus.getY(), this);
		}
		else if (Magnus.determineDirection == 1)
		{
			//Walking left
			g.drawImage(Magnus.magnusStill, magnus.getX(), magnus.getY(), this);
		}
		else
		{
			g.drawImage(Magnus.magnusStill, magnus.getX(), magnus.getY(), this);
		}*/
		
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
