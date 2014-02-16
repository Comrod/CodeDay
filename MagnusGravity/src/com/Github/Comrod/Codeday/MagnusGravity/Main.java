package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {
	
	static JFrame frame = new JFrame();
	
	public static void main(String[] args)
	{
		//Frame
		frame.setSize(Game.WIDTH, Game.HEIGHT);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		RunGame();
		
	}
	
	public static void RunGame()
	{
		final Game game = new Game();
		final Magnus magnus = new Magnus();
		
		//Frame
		frame.add(game);
		game.setVisible(true);
		frame.revalidate();
		frame.repaint();
			
	}
}
