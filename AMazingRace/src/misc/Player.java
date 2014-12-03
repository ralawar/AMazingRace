package misc;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {

	private int x, y, size, speed;

	public Player(int x, int y) {

		this.x = x;
		this.y = y;
		size = 8;
		speed = 3;
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

	public int getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, size, size);
	}

}
