package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

/*
 * Class: AMazingRace.java
 * Author: Raid Alawar
 * Purpose: The "A Mazing Race" game is started from this class.
 * It helps set up the game window as well.
 * 
 */

public class AMazingRace {

	public static void main(String[] args) {

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int screenWidth = gd.getDisplayMode().getWidth();
		int screenHeight = gd.getDisplayMode().getHeight();

		JFrame window = new JFrame("A Mazing Race");
		window.setContentPane(new GamePanel());
		window.setResizable(false);
		window.setLocation(screenWidth / 4, screenHeight / 4);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);

	}

}
