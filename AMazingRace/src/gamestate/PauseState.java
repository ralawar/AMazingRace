package gamestate;

import handler.Keys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class PauseState extends GameState {

	private int currentChoice = 0;
	private String[] options = { "Continue", "Restart", "Quit" };

	private Color pauseColor;
	private Font pauseFont;
	private Color menuDefaultColor;
	private Color menuSelectColor;
	private Font menuFont;

	public PauseState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	protected void init() {

		pauseColor = Color.WHITE;
		pauseFont = new Font("Century Gothic", Font.PLAIN, 76);

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

		// draw pause title
		g.setColor(pauseColor);
		g.setFont(pauseFont);
		g.drawString("Paused", 275, 160);

		// draw menu

		g.setFont(menuFont);
		for (int i = 0; i < options.length; i++) {
			if (currentChoice == i) {
				g.setColor(menuSelectColor);
			} else {
				g.setColor(menuDefaultColor);
			}
			g.drawString(options[i], 325, 280 + i * 70);

		}

	}

	public void select() {
		if (currentChoice == 0) {
			gsm.setPaused(false);
		} else if (currentChoice == 1) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.LEVELSTATE);
			currentChoice = 0;
		} else if (currentChoice == 2) {
			System.exit(0);
		}
	}

	protected void handleInput() {
		
		if(Keys.isPressed(Keys.ESCAPE)){
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
