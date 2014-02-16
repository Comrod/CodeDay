package com.Github.Comrod.Codeday.MagnusGravity;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	static JFrame frame = new JFrame();
	
	public static JLabel label = new JLabel();
	
	public static void main(String[] args)
	{
		//Frame
		frame.setSize(Game.WIDTH, Game.HEIGHT);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(label);
		
		label.setVisible(true);
		label.setText("Stuff");
		label.setLocation(80, 80);
		label.setSize(10, 30);
		
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
		System.out.println("Game started");
			
	}
}
