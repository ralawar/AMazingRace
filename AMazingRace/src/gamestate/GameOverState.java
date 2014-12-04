package gamestate;

import handler.Keys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

/*
 * Class: GameOverState.java
 * Author: Raid Alawar
 * Purpose: This is a game state that prints out the game 
 * results and gives the player options on how to proceed.
 * 
 */

public class GameOverState extends GameState {

	private int currentChoice;
	private String[] options;

	private Color resultsColor;
	private Font resultsFont;
	private Color menuDefaultColor;
	private Color menuSelectColor;
	private Font menuFont;

	public GameOverState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	protected void init() {

		currentChoice = 0;
		options = new String[] { "New Game", "Quit" };

		resultsColor = Color.WHITE;
		resultsFont = new Font("Century Gothic", Font.PLAIN, 76);

		menuDefaultColor = Color.WHITE;
		menuSelectColor = Color.RED;
		menuFont = new Font("Arial", Font.PLAIN, 40);
	}

	protected void update() {
		handleInput();
	}

	protected void draw(Graphics2D g) {

		// draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		// draw results
		g.setColor(resultsColor);
		g.setFont(resultsFont);
		if (gsm.playerWon()) {
			g.drawString("You win!", 235, 170);
		} else {
			g.drawString("You Lose!", 220, 160);
		}

		// draw menu
		g.setFont(menuFont);
		for (int i = 0; i < options.length; i++) {
			if (currentChoice == i) {
				g.setColor(menuSelectColor);
			} else {
				g.setColor(menuDefaultColor);
			}
			g.drawString(options[i], 290, 310 + i * 70);

		}

	}

	public void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.LEVELSTATE);
		} else if (currentChoice == 1) {
			System.exit(0);
		}
	}

	protected void handleInput() {

		if (Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(false);
			Keys.update();
		}

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

}
