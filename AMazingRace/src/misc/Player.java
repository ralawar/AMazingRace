package misc;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class Player {

	private int x, y;

	public Player(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void update() {

	}

	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, GamePanel.WIDTH / 80, GamePanel.HEIGHT / 60);
	}

}
