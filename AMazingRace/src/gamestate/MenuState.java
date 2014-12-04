package gamestate;

import handler.Keys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

/*
 * Class: MenuState.java
 * Author: Raid Alawar
 * Purpose: This is the first game state. It gives the player
 * options to start the game or quit.
 * 
 */

public class MenuState extends GameState {

	private int currentChoice;
	private String[] options;

	private Color titleColor;
	private Font titleFont;
	private Color menuDefaultColor;
	private Color menuSelectColor;
	private Font font1;
	private Font font2;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	public void init() {

		currentChoice = 0;
		options = new String[] { "Start", "Quit" };

		titleColor = Color.WHITE;
		titleFont = new Font("Times New Roman", Font.PLAIN, 76);

		menuDefaultColor = Color.WHITE;
		menuSelectColor = Color.RED;
		font1 = new Font("Arial", Font.PLAIN, 45);
		font2 = new Font("Arial", Font.PLAIN, 18);
	}

	public void update() {
		handleInput();
	}

	protected void handleInput() {
		if (Keys.isPressed(Keys.ENTER)) {
			select();
		}

		if (Keys.isPressed(Keys.UP)) {
			if (currentChoice > 0) {
				currentChoice--;
			}
		}
		if (Keys.isPressed(Keys.DOWN)) {
			if (currentChoice < options.length - 1) {
				currentChoice++;
			}
		}

	}

	public void draw(Graphics2D g) {

		// draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("A Mazing Runner", 120, 170);

		// draw menu
		g.setFont(font1);
		for (int i = 0; i < options.length; i++) {
			if (currentChoice == i) {
				g.setColor(menuSelectColor);
			} else {
				g.setColor(menuDefaultColor);
			}
			g.drawString(options[i], 325, 330 + i * 80);

		}

		// draw date and creator name
		g.setFont(font2);
		g.setColor(Color.WHITE);
		g.drawString("2014 Raid A.", 10, GamePanel.HEIGHT - 10);

	}

	public void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.LEVELSTATE);
		} else if (currentChoice == 1) {
			System.exit(0);
		}
	}

}
