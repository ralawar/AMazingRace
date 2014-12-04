package misc;

import gamestate.GameStateManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/*
 * Class: Timer.java
 * Author: Raid Alawar
 * Purpose: Draws and tracks the game time. When the timer reaches
 * zero, the game ends.
 * 
 */

public class Timer {

	int time, oldTime, currentTime;
	GameStateManager gsm;

	public Timer(int time, GameStateManager gsm) {
		this.time = time;
		this.gsm = gsm;
		oldTime = (int) (System.currentTimeMillis() / 1000);
	}

	public void update() {

		if (time > 0) {
			currentTime = (int) (System.currentTimeMillis() / 1000);
			time -= Math.abs(currentTime - oldTime);
			oldTime = currentTime;
		} else {
			gsm.setState(GameStateManager.GAMEOVERSTATE);
		}

	}

	public void draw(Graphics2D g) {

		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		g.drawString(String.format("%02d", time / 60) + ":" + String.format("%02d", time % 60), 10,
				20);

	}

}
