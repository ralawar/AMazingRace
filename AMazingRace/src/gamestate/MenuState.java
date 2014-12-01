package gamestate;

import handler.Keys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class MenuState extends GameState {

	private int currentChoice = 0;
	private String[] options = { "Start", "Quit" };

	private Color titleColor;
	private Font titleFont;
	private Color menuDefaultColor;
	private Color menuSelectColor;
	private Font font1;
	private Font font2;

	public MenuState(GameStateManager gsm) {

		super(gsm);

		titleColor = Color.WHITE;
		titleFont = new Font("Times New Roman", Font.PLAIN, 28);

		menuDefaultColor = Color.WHITE;
		menuSelectColor = Color.RED;
		font1 = new Font("Arial", Font.PLAIN, 14);
		font2 = new Font("Arial", Font.PLAIN, 10);

	}

	public void init() {
	}

	public void update() {
		handleInput();
	}

	private void handleInput() {
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
		g.drawString("A Mazing Runner", 70, 90);

		// draw menu
		g.setFont(font1);
		for (int i = 0; i < options.length; i++) {
			if (currentChoice == i) {
				g.setColor(menuSelectColor);
			} else {
				g.setColor(menuDefaultColor);
			}
			g.drawString(options[i], 145, 165 + i * 20);

		}

		// draw creator name and date
		g.setFont(font2);
		g.setColor(Color.WHITE);
		g.drawString("2014 Raid A.", 10, 232);

	}

	public void select() {
		if (currentChoice == 0) {
			// PlayerSave.init();
			gsm.setState(GameStateManager.LEVELSTATE);
		} else if (currentChoice == 1) {
			System.exit(0);
		}
	}

	public void keyPressed(int k) {

	}

	public void keyReleased(int k) {

	}

}
